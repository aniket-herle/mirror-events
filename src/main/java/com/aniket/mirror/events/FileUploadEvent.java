package com.aniket.mirror.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonCreator
  public FileUploadEvent(
      @JsonProperty("eventId") String eventId,
      @JsonProperty("fileId") String fileId,
      @JsonProperty("fileName") String fileName,
      @JsonProperty("contentType") String contentType,
      @JsonProperty("sizeBytes") long sizeBytes,
      @JsonProperty("s3Bucket") String s3Bucket,
      @JsonProperty("s3Key") String s3Key,
      @JsonProperty("s3Url") String s3Url,
      @JsonProperty("checksum") String checksum,
      @JsonProperty("createdAt") Instant createdAt,
      @JsonProperty("version") int version
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

  @Override
  public String toString() {
    return "{\n" +
        "  \"eventId\": \"" + eventId + "\",\n" +
        "  \"fileId\": \"" + fileId + "\",\n" +
        "  \"fileName\": \"" + fileName + "\",\n" +
        "  \"contentType\": \"" + contentType + "\",\n" +
        "  \"sizeBytes\": " + sizeBytes + ",\n" +
        "  \"s3Bucket\": \"" + s3Bucket + "\",\n" +
        "  \"s3Key\": \"" + s3Key + "\",\n" +
        "  \"s3Url\": \"" + s3Url + "\",\n" +
        "  \"checksum\": \"" + checksum + "\",\n" +
        "  \"createdAt\": \"" + createdAt + "\",\n" +
        "  \"version\": " + version + "\n" +
        "}";
  }

}
