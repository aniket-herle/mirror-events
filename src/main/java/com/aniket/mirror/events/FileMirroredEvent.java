package com.aniket.mirror.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public final class FileMirroredEvent {

  private final String fileId;
  private final String providerName;
  private final String status;
  private final String externalUrl;
  private final String errorMessage;
  private final Instant mirroredAt;

  @JsonCreator
  public FileMirroredEvent(
      @JsonProperty("fileId") String fileId,
      @JsonProperty("providerName") String providerName,
      @JsonProperty("status") String status,
      @JsonProperty("externalUrl") String externalUrl,
      @JsonProperty("errorMessage") String errorMessage,
      @JsonProperty("mirroredAt") Instant mirroredAt
  ) {
    this.fileId = fileId;
    this.providerName = providerName;
    this.status = status;
    this.externalUrl = externalUrl;
    this.errorMessage = errorMessage;
    this.mirroredAt = mirroredAt;
  }

  public String getFileId() { return fileId; }
  public String getProviderName() { return providerName; }
  public String getStatus() { return status; }
  public String getExternalUrl() { return externalUrl; }
  public String getErrorMessage() { return errorMessage; }
  public Instant getMirroredAt() { return mirroredAt; }

  @Override
  public String toString() {
    return "{\n" +
        "  \"fileId\": \"" + fileId + "\",\n" +
        "  \"providerName\": \"" + providerName + "\",\n" +
        "  \"status\": \"" + status + "\",\n" +
        "  \"externalUrl\": \"" + externalUrl + "\",\n" +
        "  \"errorMessage\": \"" + errorMessage + "\",\n" +
        "  \"mirroredAt\": \"" + mirroredAt + "\"\n" +
        "}";
  }
}
