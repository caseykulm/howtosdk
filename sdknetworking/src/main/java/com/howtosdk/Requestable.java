package com.howtosdk;

import okhttp3.Request;

public interface Requestable {
  Request toRequest(ApiService apiService);
}
