package com.yicheng.tourism.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author
 * @Date
 * @Desc: Http请求工具类 .
 * @Version v1.0.0
 */

public final class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private HttpClientUtil() {
	}

	/**
	 * 发送get请求
	 */
	public static String sendGetRequest(String url) {
		HttpGet httpGet;
		String result = null;
		try {
			httpGet = new HttpGet(new URL(url).toURI());
			result = doGetRequest(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送post请求
	 */
	public static String sendPostRequest(String url, String data,
			Map<String, String> header){
		HttpPost httpPost;
		String result = null;
		try {
			httpPost = new HttpPost(new URL(url).toURI());
			if (header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			StringEntity dataEntity = new StringEntity(data,
					ContentType.APPLICATION_JSON);
			httpPost.setEntity(dataEntity);
			result = doPostRequest(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	/**
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String sendPostRequest(String url, String data){
		return sendPostRequest(url,data,null);
	}

	private static String doGetRequest(HttpGet httpGet) throws Exception {
		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			if (null != httpclient) {
				response = httpclient.execute(httpGet);
			}
			return doResPonse(response);
		}  finally {
			if (null != httpclient) {
				httpclient.close();
			}
		}
	}

	private static String doPostRequest(HttpPost httpPost) throws Exception {
		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			if (null != httpclient) {
				response = httpclient.execute(httpPost);
			}
			return doResPonse(response);
		} finally {
			if (null != httpclient) {
				httpclient.close();
			}
		}
	}

	private static String doResPonse(CloseableHttpResponse response)
			throws Exception {
		BufferedReader reader = null;
		try {
			if (null != response
					&& response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (null == entity) {
					throw new Exception("error code entity is null "
							+ response.getStatusLine().getStatusCode() + ":"
							+ response.getStatusLine().getReasonPhrase());
				}
				reader = new BufferedReader(
						new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8));
				StringBuilder buffer = new StringBuilder();
				String tempStr;
				while ((tempStr = reader.readLine()) != null) {
					buffer.append(tempStr);
				}

				return buffer.toString();

			} else if (null != response
					&& response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("error code "
						+ response.getStatusLine().getStatusCode() + ":"
						+ response.getStatusLine().getReasonPhrase());
			}
			return null;
		} finally {
			if (null != response) {
				response.close();
			}
			if (null != reader) {
				reader.close();
			}
		}
	}
	
	/**
	 * 发起get 请求
	 */
	public static String sendGetRequest2(String url){
		BufferedReader in = null;
		URLConnection connection =null;
		try{
			URL realUrl = new URL(url);
			connection = realUrl.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line; 
			StringBuilder result = new StringBuilder();
	        while ((line = in.readLine()) != null) {  
	        	result.append(line);  
	        }
	        return result.toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(null !=in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
