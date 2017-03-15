package com.howtosdk;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.assertTrue;

public class HowToSdkTest {
  @Test
  public void buildItUp() {
    HowToSdk howToSdk = new HowToSdk.Builder()
        .build();
  }

  @Test
  public void sendRequest() throws Exception {
    HowToSdk howToSdk = new HowToSdk.Builder()
        .build();

    Request request = new Request.Builder()
        .url("https://jsonplaceholder.typicode.com/users")
        .build();

    Call call = howToSdk.getRestManager()
        .newCall(request);

    Response response = call.execute();

    assertTrue("response failed", response.isSuccessful());
  }
}
