package com.salvatore.cinemates.utils;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;

public final class NetworkUtils {
	private static HttpUrl urlCreator (String baseUrl, String pathComponent, Map<String, String> params) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + pathComponent).newBuilder();
		for (Map.Entry<String, String> paramsCouple: params.entrySet()) {
			urlBuilder.addEncodedQueryParameter(paramsCouple.getKey(), paramsCouple.getValue());
		}
		return urlBuilder.build();
	}
	
	private static Request requestCreator(HttpUrl url, Map<String, String> headers) {
		Headers heads = Headers.of(headers);
		return new Request.Builder().url(url).headers(heads).build();
	}
	
	public static Request createRequest(String baseUrl, String pathComponents, Map<String, String> params, Map<String, String> headers) {
		return requestCreator(urlCreator(baseUrl, pathComponents, params), headers);
	}
}
