package com.aniket.mirror.events;

import java.time.Instant;

public final class FileUploadEvent {

  private final String eventId;
  private final String fileId;
  private final String fileName;
  private final String contentType;
  private final long sizeBytes;
  private final String s3Bucket;
  private final String s3Key;
  private final String s3Url;
  private final String checksum;
  private final Instant createdAt;
  private final int version;

  public FileUploadEvent(
      String eventId,
      String fileId,
      String fileName,
      String contentType,
      long sizeBytes,
      String s3Bucket,
      String s3Key,
      String s3Url,
      String checksum,
      Instant createdAt,
      int version
  ) {
    this.eventId = eventId;
    this.fileId = fileId;
    this.fileName = fileName;
    this.contentType = contentType;
    this.sizeBytes = sizeBytes;
    this.s3Bucket = s3Bucket;
    this.s3Key = s3Key;
    this.s3Url = s3Url;
    this.checksum = checksum;
    this.createdAt = createdAt;
    this.version = version;
  }

  public String getEventId() { return eventId; }
  public String getFileId() { return fileId; }
  public String getFileName() { return fileName; }
  public String getContentType() { return contentType; }
  public long getSizeBytes() { return sizeBytes; }
  public String getS3Bucket() { return s3Bucket; }
  public String getS3Key() { return s3Key; }
  public String getS3Url() { return s3Url; }
  public String getChecksum() { return checksum; }
  public Instant getCreatedAt() { return createdAt; }
  public int getVersion() { return version; }
}
