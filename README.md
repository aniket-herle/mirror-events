# File Mirror - Kafka Events

[![Java](https://img.shields.io/badge/Java-100%25-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-Central-blue.svg)](https://search.maven.org/)

Shared event definitions and data models for the Multi Mirror Project. This library provides strongly-typed, immutable event classes for Kafka-based communication between microservices.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Events](#events)
- [Usage](#usage)
- [Event Schemas](#event-schemas)
- [Serialization](#serialization)
- [Versioning](#versioning)

## Overview

The `mirror-events` library serves as the contract layer for event-driven communication in the Multi Mirror ecosystem. It ensures:

- **Type Safety**: Compile-time verification of event structures
- **Immutability**: Thread-safe, immutable event objects
- **Consistency**: Single source of truth for event definitions
- **Backward Compatibility**: Versioned events for schema evolution

## Features

- ✅ **Immutable Event Classes**: All events are final with immutable fields
- ✅ **Jackson Integration**: Seamless JSON serialization/deserialization
- ✅ **Null Safety**: Comprehensive null handling for optional fields
- ✅ **Version Support**: Built-in versioning for schema evolution
- ✅ **Zero Dependencies**: Minimal footprint with only Jackson annotations
- ✅ **Maven/Gradle Compatible**: Easy integration into any Java project

## Events

### 1. FileUploadEvent

**Purpose**: Published when a file upload to S3 is completed.

**Producer**: Upload Service  
**Consumers**: Replicator Service  
**Topic**: `file_upload`

**Fields**:
- `eventId` (String): Unique event identifier
- `fileId` (String): File identifier
- `fileName` (String): Original file name
- `contentType` (String): MIME type
- `sizeBytes` (long): File size in bytes
- `s3Bucket` (String): S3 bucket name
- `s3Key` (String): S3 object key
- `s3Url` (String): Full S3 URL
- `checksum` (String, nullable): SHA-256 checksum
- `createdAt` (Instant): Upload timestamp
- `version` (int): Event schema version

### 2. FileMirroredEvent

**Purpose**: Published when file replication to a provider completes (success or failure).

**Producer**: Replicator Service  
**Consumers**: Upload Service, Monitoring Services  
**Topic**: `file_mirrored`

**Fields**:
- `fileId` (String): File identifier
- `providerName` (String): Storage provider (e.g., "STREAM_TAPE")
- `status` (String): "UPLOADED" or "FAILED"
- `externalUrl` (String, nullable): Provider's file URL (if successful)
- `errorMessage` (String, nullable): Error details (if failed)
- `mirroredAt` (Instant): Completion timestamp

### 3. FileMirrorCheckEvent

**Purpose**: Triggers health check and repair for a specific file mirror.

**Producer**: Upload Service, Scheduled Jobs  
**Consumers**: Replicator Service  
**Topic**: `file_mirror_check`

**Fields**:
- `fileId` (String): File identifier
- `providerName` (String): Storage provider to check

## Usage

### Installation

#### Gradle

```gradle
dependencies {
    implementation 'com.aniket.mirror:mirror-events:1.0.0-SNAPSHOT'
}
```

#### Maven

```xml
<dependency>
    <groupId>com.aniket.mirror</groupId>
    <artifactId>mirror-events</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Producing Events

#### Example: Publishing FileUploadEvent

```java
import com.aniket.mirror.events.FileUploadEvent;
import org.springframework.kafka.core.KafkaTemplate;
import java.time.Instant;
import java.util.UUID;

@Service
public class FileUploadProducer {
    
    @Autowired
    private KafkaTemplate<String, FileUploadEvent> kafkaTemplate;
    
    public void publishFileUpload(String fileId, String fileName, long sizeBytes, 
                                   String s3Bucket, String s3Key, String s3Url) {
        FileUploadEvent event = new FileUploadEvent(
            UUID.randomUUID().toString(),  // eventId
            fileId,                          // fileId
            fileName,                        // fileName
            "application/octet-stream",     // contentType
            sizeBytes,                       // sizeBytes
            s3Bucket,                        // s3Bucket
            s3Key,                           // s3Key
            s3Url,                           // s3Url
            "abc123def456...",              // checksum
            Instant.now(),                   // createdAt
            1                                // version
        );
        
        kafkaTemplate.send("file_upload", fileId, event);
    }
}
```

#### Example: Publishing FileMirroredEvent

```java
import com.aniket.mirror.events.FileMirroredEvent;

@Service
public class FileMirrorProducer {
    
    @Autowired
    private KafkaTemplate<String, FileMirroredEvent> kafkaTemplate;
    
    public void publishSuccess(String fileId, String providerName, String externalUrl) {
        FileMirroredEvent event = new FileMirroredEvent(
            fileId,
            providerName,
            "UPLOADED",
            externalUrl,
            null,               // no error
            Instant.now()
        );
        
        kafkaTemplate.send("file_mirrored", fileId, event);
    }
    
    public void publishFailure(String fileId, String providerName, String errorMessage) {
        FileMirroredEvent event = new FileMirroredEvent(
            fileId,
            providerName,
            "FAILED",
            null,               // no URL
            errorMessage,
            Instant.now()
        );
        
        kafkaTemplate.send("file_mirrored", fileId, event);
    }
}
```

### Consuming Events

#### Example: Consuming FileUploadEvent

```java
import com.aniket.mirror.events.FileUploadEvent;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class FileUploadConsumer {
    
    @KafkaListener(topics = "file_upload", groupId = "replicator-service")
    public void handleFileUpload(FileUploadEvent event) {
        log.info("Received file upload event: fileId={}, fileName={}", 
                 event.getFileId(), event.getFileName());
        
        // Process the uploaded file
        replicateFileToProviders(event);
    }
}
```

#### Example: Consuming FileMirrorCheckEvent

```java
import com.aniket.mirror.events.FileMirrorCheckEvent;

@Service
public class FileMirrorCheckConsumer {
    
    @KafkaListener(topics = "file_mirror_check", groupId = "replicator-service")
    public void handleMirrorCheck(FileMirrorCheckEvent event) {
        log.info("Received mirror check request: fileId={}, provider={}", 
                 event.getFileId(), event.getProviderName());
        
        // Verify mirror health and repair if needed
        checkAndRepairMirror(event.getFileId(), event.getProviderName());
    }
}
```

## Event Schemas

### FileUploadEvent JSON Schema

```json
{
  "eventId": "550e8400-e29b-41d4-a716-446655440000",
  "fileId": "file-12345",
  "fileName": "example.mp4",
  "contentType": "video/mp4",
  "sizeBytes": 52428800,
  "s3Bucket": "mirror-uploads",
  "s3Key": "files/2024/01/example.mp4",
  "s3Url": "https://s3.amazonaws.com/mirror-uploads/files/2024/01/example.mp4",
  "checksum": "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
  "createdAt": "2024-01-15T10:30:00Z",
  "version": 1
}
```

### FileMirroredEvent JSON Schema

```json
{
  "fileId": "file-12345",
  "providerName": "STREAM_TAPE",
  "status": "UPLOADED",
  "externalUrl": "https://streamtape.com/v/abc123",
  "errorMessage": null,
  "mirroredAt": "2024-01-15T10:35:00Z"
}
```

### FileMirrorCheckEvent JSON Schema

```json
{
  "fileId": "file-12345",
  "providerName": "STREAM_TAPE"
}
```

## Serialization

All event classes use Jackson annotations for JSON serialization:

- **@JsonCreator**: Enables JSON deserialization via constructor
- **@JsonProperty**: Maps JSON fields to constructor parameters
- **Immutable Fields**: All fields are `final` to ensure thread safety

### Custom Configuration

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Bean
public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
}
```

## Versioning

Events support versioning for backward compatibility:

- **Current Version**: All events are at version 1
- **Version Field**: `FileUploadEvent` includes explicit `version` field
- **Future Changes**: New optional fields can be added without breaking existing consumers
- **Breaking Changes**: Require version increment and dual-consumer support during transition

### Version Evolution Strategy

1. **Adding Optional Fields**: No version change needed
2. **Removing Fields**: Increment version, deprecate old version
3. **Changing Field Types**: Create new event class with version suffix

## Build

```bash
# Build the library
./gradlew build

# Publish to local Maven repository
./gradlew publishToMavenLocal
```

## License

This project is part of the Multi Mirror Project.

## Contributing

This is a shared library. All changes must be:
- Backward compatible (unless major version bump)
- Documented in this README
- Coordinated with service teams
