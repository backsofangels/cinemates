package com.salvatore.cinemates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salvatore.cinemates.client.MovieSearchHttpClient;

@Service
public class MovieSearchService {
	@Autowired
	private MovieSearchHttpClient httpClient;
}
