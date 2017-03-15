package com.howtosdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import okhttp3.HttpUrl;
import okhttp3.Request;

public class RedAppleRequest implements Requestable {
  private final String requiredFoo;
  private final String optionalBar;

  public RedAppleRequest(@NonNull String requiredFoo, @Nullable String optionalBar) {
    this.requiredFoo = requiredFoo;
    this.optionalBar = optionalBar;
  }

  @NonNull
  public String getRequiredFoo() {
    return requiredFoo;
  }

  @Nullable
  public String getOptionalBar() {
    return optionalBar;
  }

  @Override
  public Request toRequest(ApiService apiService) {
    HttpUrl rootUrl = apiService.getRootUrl();
    HttpUrl.Builder urlBuilder = rootUrl.newBuilder();
    HttpUrl absoluteUrl = urlBuilder.addEncodedPathSegment("v1")
        .addEncodedPathSegment("apples")
        .addEncodedPathSegment("red")
        .build();
    return new Request.Builder()
        .url(absoluteUrl)
        .build();
  }
}