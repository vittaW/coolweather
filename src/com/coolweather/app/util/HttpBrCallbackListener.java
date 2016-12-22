package com.coolweather.app.util;

import java.io.InputStream;

public interface HttpBrCallbackListener {
	void onFinish(InputStream is);
	void onError(Exception e);
}
