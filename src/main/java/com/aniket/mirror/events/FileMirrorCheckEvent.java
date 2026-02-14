package com.aniket.mirror.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class FileMirrorCheckEvent {

  private final String fileId;
  private final String providerName;

  @JsonCreator
  public FileMirrorCheckEvent(
      @JsonProperty("fileId") String fileId,
      @JsonProperty("providerName") String providerName
  ) {
    this.fileId = fileId;
    this.providerName = providerName;
  }

  public String getFileId() { return fileId; }
  public String getProviderName() { return providerName; }

  @Override
  public String toString() {
    return "{\n" +
        "  \"fileId\": \"" + fileId + "\",\n" +
        "  \"providerName\": \"" + providerName + "\"\n" +
        "}";
  }
}
