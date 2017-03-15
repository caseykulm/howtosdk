package com.howtosdk;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestManager {
  private final OkHttpClient okHttpClient;
  private final Gson gson;

  RestManager(@NonNull OkHttpClient okHttpClient, @NonNull Gson gson) {
    this.okHttpClient = okHttpClient;
    this.gson = gson;
  }

  Call newCall(Request request) {
    return okHttpClient.newCall(request);
  }

  public <RequestType extends Requestable> Call get(ApiService apiService, RequestType request) {
    Request okHttpRequest = request.toRequest(apiService);
    return newCall(okHttpRequest);
  }

  <ResponseType> ResponseType parse(Response response, Class<ResponseType> clazz) throws IOException {
    if (!response.isSuccessful()) {
      throw new IllegalStateException("Response failed with code: " + response.code());
    }
    String responseStr = response.body().string();
    return gson.fromJson(responseStr, clazz);
  }
}
