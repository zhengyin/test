package com.izhengyin.test.other.rpc;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUnits {
	
	private static final int DEFAULT_CONNECT_TIMEOUT = 1000;
	private static final int DEFAULT_SOCKET_TIMEOUT  = 3000;
	
	
	public static String get(String url) throws IOException{
		return get(url,DEFAULT_CONNECT_TIMEOUT,DEFAULT_SOCKET_TIMEOUT);
	}
	/**
	 * GET Request
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String get(String url , int connectTimeout , int socketTimeout) throws IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().
				setConnectTimeout(connectTimeout).
				setSocketTimeout(socketTimeout).build();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		String result = "";
		try {
			response = httpclient.execute(httpGet);
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity, "UTF-8");
		    EntityUtils.consume(entity);
		} finally {
			if(response != null){
				response.close();
			}
		}
		return result;
	}
	
	public static String post(String url , Map<String, Object> params) throws IOException{
		return post(url, params, DEFAULT_CONNECT_TIMEOUT ,DEFAULT_SOCKET_TIMEOUT);
	}
	
	/**
	 * POST request
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url , Map<String, Object> params, int connectTimeout , int socketTimeout) throws IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().
				setConnectTimeout(connectTimeout).
				setSocketTimeout(socketTimeout).build();
		httpPost.setConfig(requestConfig);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		for(Entry<String, Object> entry : params.entrySet()){
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()+""));
		}
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
		CloseableHttpResponse response = null;
		String result = "";
		try {
			response = httpclient.execute(httpPost);
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity, "UTF-8");
		    EntityUtils.consume(entity);
		} finally {
			if(response != null){
				response.close();
			}
		}
		return result;
	}


	/**
	 * 将url参数解析成Map
	 * @param queryString
	 * @return Map
	 */
	public static Map<String, Object> getUrlParams(String queryString) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(queryString)) {
			return map;
		}
		String[] params = queryString.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将Map解析成url参数
	 * @param map
	 * @return String
	 */
	public static  String getQueryString(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}



}
