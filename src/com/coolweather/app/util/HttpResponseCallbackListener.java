package com.coolweather.app.util;

public interface HttpResponseCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
