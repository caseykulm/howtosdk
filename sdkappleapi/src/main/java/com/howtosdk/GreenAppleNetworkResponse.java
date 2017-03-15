package com.howtosdk;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

class GreenAppleNetworkResponse {
  static GreenAppleResponse parse(RestManager restManager, Response response) throws IOException, IllegalStateException {
    GreenAppleNetworkResponse greenAppleNetworkResponse = restManager.parse(response, GreenAppleNetworkResponse.class);
    return map(greenAppleNetworkResponse);
  }

  private static GreenAppleResponse map(GreenAppleNetworkResponse greenAppleNetworkResponse) throws IllegalStateException {
    List<Result> results = greenAppleNetworkResponse.getResults();
    if (results == null || results.size() != 1) {
      throw new IllegalStateException("Unexpected number of results. Should only be 1");
    }
    Result firstResult = results.get(0);
    String idStr = String.valueOf(firstResult.getId());
    String name = firstResult.getName();
    return new GreenAppleResponse(idStr, name);
  }

  @SerializedName("the_results")
  private List<Result> results;

  public List<Result> getResults() {
    return results;
  }

  static class Result {
    @SerializedName("the_id")
    private int id;
    @SerializedName("the_name")
    private String name;

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }
}
