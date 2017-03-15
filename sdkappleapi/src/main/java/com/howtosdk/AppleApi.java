package com.howtosdk;

import android.support.annotation.NonNull;

import okhttp3.HttpUrl;

public class AppleApi implements ApiService {
  private final HowToSdk howToSdk;
  private final HttpUrl rootUrl;

  AppleApi(HowToSdk howToSdk, HttpUrl rootUrl) {
    this.howToSdk = howToSdk;
    this.rootUrl = rootUrl;
  }

  HowToSdk getHowToSdk() {
    return howToSdk;
  }

  @Override
  public HttpUrl getRootUrl() {
    return rootUrl;
  }

  public static class Builder {
    private final HowToSdk howToSdk;
    private final HttpUrl rootUrl;

    public Builder(@NonNull HowToSdk howToSdk) {
      this.howToSdk = howToSdk;
      this.rootUrl = HttpUrl.parse("http://localhost:8000/appleapi");
    }

    public AppleApi build() {
      return new AppleApi(howToSdk, rootUrl);
    }
  }
}
