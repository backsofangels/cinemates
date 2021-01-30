package com.salvatore.cinemates.client;

import java.util.Hashtable;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.salvatore.cinemates.utils.HttpLoggingInterceptor;
import com.salvatore.cinemates.utils.NetworkUtils;
import com.salvatore.cinemates.utils.TmdbRequestLocale;

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
		Hashtable<String, String> params = new Hashtable<>();
		params.put("query", movieTitle);
		params.put("language", "it-IT");
		Hashtable<String, String> headers = new Hashtable<>();
		headers.put("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty("tmdb.baseUrl"), env.getProperty("tmdb.search.movie"), params, headers);
		Response response = null;
		try {
			response = client.newCall(request).execute();
			String resp = response.body().string();
			return resp;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			if(response != null) {
				response.close();
			}
			logger.info("END METHOD searchByTitle");
		}
	}
	
	public String retrieveCastDetailsForMovie(int movieId) {
		logger.info("BEGIN METHOD retrieveCastDetailsForMovie");
		Hashtable<String, String> params = new Hashtable<>();
		params.put("language", TmdbRequestLocale.IT_VALUE);
		Hashtable<String, String> headers = new Hashtable<>();
		headers.put("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty("tmdb.baseUrl"), String.format(env.getProperty("tmdb.movie.castDetails"), movieId), params, headers);
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD searchPerson");
		}
	}
	
	public String searchPerson(String personName) {
		logger.info("BEGIN METHOD searchPerson");
		Hashtable<String, String> params = new Hashtable<>();
		params.put("query", personName);
		params.put("language", TmdbRequestLocale.IT_VALUE);
		Hashtable<String, String> headers = new Hashtable<>();
		headers.put("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty("tmdb.baseUrl"), env.getProperty("tmdb.search.movie"), params, headers);
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD searchPerson");
		}
	}
	
	@SuppressWarnings("resource")
	public String searchPersonDetails(int personId) {
		logger.info("BEGIN METHOD searchPersonDetails");
		Hashtable<String, String> params = new Hashtable<>();
		params.put("language", "it-IT");
		Hashtable<String, String> headers = new Hashtable<>();
		headers.put("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty("tmdb.baseUrl"), String.format(env.getProperty("tmdb.person.details"), personId), params, headers);
		Response response = null;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD searchPersonDetails");
		}
	}
	
	public String retrieveMovieDetails(int movieId) {
		logger.info("BEGIN METHOD retrieveMovieDetails");
		Hashtable<String, String> params = new Hashtable<>();
		params.put("language", TmdbRequestLocale.IT_VALUE);
		Hashtable<String, String> headers = new Hashtable<>();
		headers.put("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty("tmdb.baseUrl"), String.format(env.getProperty("tmdb.movie.details"), movieId), params, headers);
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD retrieveMovieDetails");
		}	
	}
	
	public String getKeywordsForMovieId(int id) {
		logger.info("BEGIN METHOD getKeywordsForMovieId");
		Hashtable<String, String> emptyParams = new Hashtable<>();
		Hashtable<String, String> headers = new Hashtable<>();
		headers.put("Authorization", "Bearer " + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty("tmdb.baseUrl"), String.format(env.getProperty("tmdb.movie.keywords"), id), emptyParams, headers);
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			if (response != null) {
				response.close();
			}
			logger.info("END METHOD getKeywordsForMovieId");
		}
	}
}
