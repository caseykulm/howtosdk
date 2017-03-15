package com.howtosdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;

public class AppleApiTest {
  @Test
  public void buildItUp() {
    OkHttpClient okHttpClient = new OkHttpClient();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    RestManager restManager = createRestManager(okHttpClient, gson);
    HttpUrl rootUrl = HttpUrl.parse("/");
    HowToSdk howToSdk = createHowToSdk(restManager);
    createAppleApi(howToSdk, rootUrl);
  }

  @Test
  public void sendGreenAppleRequest() throws Exception {
    // Create Test Server
    MockWebServer server = new MockWebServer();

    // Enqueue Test Response
    String testResponseStr = jsonFileToString("green_response.json");
    MockResponse mockResponse = new MockResponse()
        .setResponseCode(200)
        .setBody(testResponseStr);
    server.enqueue(mockResponse);

    // Start Test Server
    HttpUrl testHttpUrl = server.url("/applesapi/");

    // Construct Apple Library Request
    GreenAppleRequest greenAppleRequest = new GreenAppleRequest("foo", "bar");

    // Setup Apple Library Dependencies
    HowToSdk howToSdk = createBasicHowToSdk();
    AppleApi appleApi = createAppleApi(howToSdk, testHttpUrl);

    // Get OkHttp Call from OkHttp Request
    Call call = howToSdk.getRestManager().get(appleApi, greenAppleRequest);

    // Can Cancel OkHttp Calls that are in flight
    // call.cancel();

    // Get OkHttp Response from OkHttp Call (synchronously)
    Response response = call.execute();

    // Convert OkHttp Response to Apple Library Response
    GreenAppleResponse greenAppleResponse = GreenAppleResponse.parse(appleApi, response);

    assertEquals("111", greenAppleResponse.getId());
    assertEquals("Greeny", greenAppleResponse.getName());

    // Shutdown Test Server
    server.shutdown();
  }

  private AppleApi createAppleApi(HowToSdk howToSdk, HttpUrl rootUrl) {
    return new AppleApi(howToSdk, rootUrl);
  }

  private HowToSdk createHowToSdk(RestManager restManager) {
    return new HowToSdk(restManager);
  }

  private RestManager createRestManager(OkHttpClient okHttpClient, Gson gson) {
    return new RestManager(okHttpClient, gson);
  }

  private HowToSdk createBasicHowToSdk() {
    OkHttpClient okHttpClient = new OkHttpClient();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    RestManager restManager = createRestManager(okHttpClient, gson);
    HowToSdk howToSdk = createHowToSdk(restManager);
    return howToSdk;
  }

  private String jsonFileToString(String fileName) throws IOException {
    InputStream testResponseStream = getClass().getClassLoader().getResourceAsStream(fileName);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testResponseStream));
    StringBuilder stringBuilder = new StringBuilder();
    String line = "";
    while ((line = bufferedReader.readLine()) != null) {
      stringBuilder.append(line);
    }
    bufferedReader.close();
    return stringBuilder.toString();
  }
}
