package com.salvatore.cinemates.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpLoggingInterceptor implements Interceptor {
	private Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);
	private String requestUUID;
	
	public HttpLoggingInterceptor(String UUID) {
		this.requestUUID = UUID;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		long startTime = System.nanoTime();
		logger.info(String.format("TRACE_ID=%s, STEP=request, URL=%s%nHEADERS=%s", this.requestUUID, request.url(), request.headers()));
		
		Response response = chain.proceed(request);
		long endTime = System.nanoTime();
		logger.info(String.format("TRACE_ID=%s, STEP=response, URL=%s, ELAPSED=%.1fms%nHEADERS=%s", this.requestUUID, response.request().url(), 
				(endTime - startTime)/1e6d, response.headers()));
		return response;
	}
	
}
