package com.salvatore.cinemates.client;

import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.salvatore.cinemates.utils.HttpLoggingInterceptor;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MovieSearchHttpClient {
	@Autowired
	private Environment env;
	@Autowired
	private OkHttpClient client;
	private Logger logger = LoggerFactory.getLogger(MovieSearchHttpClient.class);
	
	@Bean
	public HttpLoggingInterceptor createLoggingInterceptor() {
		return new HttpLoggingInterceptor(RandomStringUtils.randomAlphanumeric(10));
	}
	
	@Bean
	public OkHttpClient createNewClient() {
		return new OkHttpClient.Builder().addInterceptor(createLoggingInterceptor()).build();
	}
	
	public String searchByTitle(String movieTitle) {
		logger.info("BEGIN METHOD searchByTitle");
		HttpUrl url = HttpUrl.parse(env.getProperty("tmdb.baseUrl") + env.getProperty("tmdb.search.movie")).newBuilder()
				.addEncodedQueryParameter("query", movieTitle)
				.addEncodedQueryParameter("language", "it-IT")
				.build();
		Request request = new Request.Builder()
				.addHeader("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"))
				.url(url)
				.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			String resp = response.body().string();
			logger.error(resp);
			return resp;
		} catch (Exception e) {
			return null;
		} finally {
			if(response != null) {
				response.close();
			}
			logger.info("END METHOD searchByTitle");
		}
	}
	
	public String searchPerson(String personName) {
		logger.info("BEGIN METHOD searchPerson");
		Hashtable<String, String> params = new Hashtable<>();
		params.put("query", personName);
		params.put("language", "it-IT");
		HttpUrl requestUrl = httpUrlCreator(env.getProperty("tmdb.baseUrl"), env.getProperty("tmdb.search.person"), params);
		Request request = new Request.Builder().addHeader("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"))
				.url(requestUrl).build();
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD searchPerson");
		}
	}
	
	public String searchPersonDetails(int personId) {
		logger.info("BEGIN METHOD searchPersonDetails");
		Hashtable<String, String> params = new Hashtable<>();
		params.put("language", "it-IT");
		HttpUrl requestUrl = httpUrlCreator(env.getProperty("tmdb.baseurl"), String.format(env.getProperty("tmdb.person.details"), personId), params);
		Request request = requestCreator(requestUrl);
		Response response = null;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch(Exception e) {
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD searchPersonDetails");
		}
	}
	
	private HttpUrl httpUrlCreator (String baseUrl, String pathComponent, Map<String, String> params) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + pathComponent).newBuilder();
		for (Map.Entry<String, String> paramsCouple: params.entrySet()) {
			urlBuilder.addEncodedQueryParameter(paramsCouple.getKey(), paramsCouple.getValue());
		}
		return urlBuilder.build();
	}
	
	private Request requestCreator(HttpUrl url) {
		return new Request.Builder().addHeader("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"))
				.url(url).build();
	}
}
