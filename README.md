# File Mirror - Kafka Events

[![Java](https://img.shields.io/badge/Java-100%25-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-Central-blue.svg)](https://search.maven.org/)

Shared event definitions and data models for the Multi Mirror Project. This library provides strongly-typed, immutable event classes for Kafka-based communication between microservices.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Events](#events)
- [Event Schemas](#event-schemas)

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

