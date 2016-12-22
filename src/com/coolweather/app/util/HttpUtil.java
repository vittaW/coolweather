package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static void sendHttpRequest(final String address,
			final HttpResponseCallbackListener listener){
		new Thread(){
			public void run() {
				URL url = null;
				HttpURLConnection conn = null;
				try {
					url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(500);
					conn.setReadTimeout(500);
					conn.setRequestProperty("Accept-Charset", "utf-8");
					conn.connect();
					if(conn.getResponseCode() == 200){
						InputStream is = conn.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
						String line = "";
						StringBuilder response = new StringBuilder();
						while((line = br.readLine()) != null){
							response.append(line);
						}
						if(listener != null){
							//回调onFinish方法
							listener.onFinish(response.toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if(listener != null){
						//回调onError方法
						listener.onError(e);
					}
				} finally{
					if(conn != null){
						conn.disconnect();
					}
				}
				
			};
		}.start();
	}
	
	public static void sendHttpRequest(final String address,
			final HttpBrCallbackListener listener){
		new Thread(){
			public void run() {
				URL url = null;
				HttpURLConnection conn = null;
				try {
					url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(500);
					conn.setReadTimeout(500);
					conn.setRequestProperty("Accept-Charset", "utf-8");
					conn.connect();
					if(conn.getResponseCode() == 200){
						InputStream is = conn.getInputStream();
						if(listener != null){
							//回调onFinish方法
							listener.onFinish(is);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if(listener != null){
						//回调onError方法
						listener.onError(e);
					}
				} finally{
					if(conn != null){
						conn.disconnect();
					}
				}
				
			};
		}.start();
	}
}
