package com.salvatore.cinemates.client;

import java.util.HashMap;

import com.salvatore.cinemates.model.Movie;
import com.salvatore.cinemates.utils.ApplicationPropertiesConstants;
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
import com.salvatore.cinemates.utils.MovieSearchHttpClientConstants;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MovieSearchHttpClient {
	@Autowired
	private Environment env;
	@Autowired
	private OkHttpClient client;
	
	private final Logger logger = LoggerFactory.getLogger(MovieSearchHttpClient.class);
	
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
		HashMap<String, String> params = new HashMap<>();
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_QUERY_FIELD, movieTitle);
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD,
				MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD_VALUE_ITALIAN);
		HashMap<String, String> headers = new HashMap<>();
		headers.put(MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION,
				MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION_BEARER +
						env.getProperty(ApplicationPropertiesConstants.TMDB_API_TOKEN));
		Request request = NetworkUtils.createRequest(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_BASEURL),
				env.getProperty(ApplicationPropertiesConstants.TMDB_URI_SEARCH_MOVIE_URL), params, headers);
		Response response = null;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
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
		HashMap<String, String> params = new HashMap<>();
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD,
				MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD_VALUE_ITALIAN);
		HashMap<String, String> headers = new HashMap<>();
		headers.put(MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION, MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION_BEARER + env.getProperty("tmdb.apiToken"));
		Request request = NetworkUtils.createRequest(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_BASEURL),
				String.format(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_CAST_DETAILS_URL), movieId), params, headers);
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
		HashMap<String, String> params = new HashMap<>();
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_QUERY_FIELD, personName);
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD,
				MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD_VALUE_ITALIAN);
		HashMap<String, String> headers = new HashMap<>();
		headers.put(MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION,
				MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION_BEARER +
						env.getProperty(ApplicationPropertiesConstants.TMDB_API_TOKEN));
		Request request = NetworkUtils.createRequest(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_BASEURL),
				env.getProperty(ApplicationPropertiesConstants.TMBD_URI_SEARCH_PERSON_URL), params, headers);
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
		HashMap<String, String> params = new HashMap<>();
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD,
				MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD_VALUE_ITALIAN);
		HashMap<String, String> headers = new HashMap<>();
		headers.put(MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION,
				MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION_BEARER +
						env.getProperty(ApplicationPropertiesConstants.TMDB_API_TOKEN));
		Request request = NetworkUtils.createRequest(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_BASEURL),
				String.format(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_PERSON_DETAILS_URL), personId), params, headers);
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
		HashMap<String, String> params = new HashMap<>();
		params.put(MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD,
				MovieSearchHttpClientConstants.HTTPCLIENT_REQUEST_LANGUAGE_FIELD_VALUE_ITALIAN);
		HashMap<String, String> headers = new HashMap<>();
		headers.put(MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION,
				MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION_BEARER +
						env.getProperty(ApplicationPropertiesConstants.TMDB_API_TOKEN));
		Request request = NetworkUtils.createRequest(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_BASEURL),
				String.format(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_MOVIE_DETAILS_URL), movieId), params, headers);
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
		HashMap<String, String> emptyParams = new HashMap<>();
		HashMap<String, String> headers = new HashMap<>();
		headers.put(MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION,
				MovieSearchHttpClientConstants.HTTPCLIENT_HEADERS_AUTHORIZATION_BEARER +
						env.getProperty(ApplicationPropertiesConstants.TMDB_API_TOKEN));
		Request request = NetworkUtils.createRequest(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_BASEURL),
				String.format(env.getProperty(ApplicationPropertiesConstants.TMDB_URI_MOVIE_KEYWORD_URL), id), emptyParams, headers);
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
