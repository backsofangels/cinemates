package com.salvatore.cinemates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salvatore.cinemates.model.Movie;
import com.salvatore.cinemates.model.Person;
import com.salvatore.cinemates.services.MovieSearchService;

@RestController
public class MovieSearchController {
	@Autowired
	private MovieSearchService searchService;
	
	@RequestMapping(value="/search/movie", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Movie> searchMovieByTitle(String title, boolean withCrewDetails) {
		return searchService.getMoviesByTitle(title);
	}
	
	@RequestMapping(value="/search/person", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Person> searchPerson(String personName) {
		return searchService.getPersons(personName);
	}
	
	@RequestMapping(value="/movie/details", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovieDetails(int movieId) {
		return searchService.getMovieDetails(movieId);
	}
	
}
