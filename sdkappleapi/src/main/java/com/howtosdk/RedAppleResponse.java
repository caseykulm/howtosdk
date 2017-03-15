package com.howtosdk;

import android.support.annotation.NonNull;

public class RedAppleResponse {
  private final String id;
  private final String name;

  public RedAppleResponse(String id, String name) {
    this.id = id;
    this.name = name;
  }

  @NonNull
  public String getId() {
    return id;
  }

  @NonNull
  public String getName() {
    return name;
  }
}

