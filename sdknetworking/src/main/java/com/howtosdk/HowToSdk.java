package com.howtosdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;

public class HowToSdk {
  private final RestManager restManager;

  HowToSdk(RestManager restManager) {
    this.restManager = restManager;
  }

  public RestManager getRestManager() {
    return restManager;
  }

  public static class Builder {
    private RestManager restManager;

    public HowToSdk build() {
      OkHttpClient okHttpClient = new OkHttpClient();
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      restManager = new RestManager(okHttpClient, gson);
      return new HowToSdk(restManager);
    }
  }
}
