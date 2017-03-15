package com.howtosdk;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Response;

public class GreenAppleResponse {
  private final String id;
  private final String name;

  public GreenAppleResponse(String id, String name) {
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

  public static GreenAppleResponse parse(AppleApi appleApi, Response response) throws IOException, IllegalStateException {
    return GreenAppleNetworkResponse.parse(appleApi.getHowToSdk().getRestManager(), response);
  }
}
