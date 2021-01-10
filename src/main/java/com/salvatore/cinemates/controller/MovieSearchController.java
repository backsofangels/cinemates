package com.salvatore.cinemates.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salvatore.cinemates.client.MovieSearchHttpClient;
import com.salvatore.cinemates.dto.TmdbMovieDto;
import com.salvatore.cinemates.dto.TmdbPersonDto;
import com.salvatore.cinemates.model.Movie;
import com.salvatore.cinemates.model.Person;

@RestController
public class MovieSearchController {
	@Autowired
	private MovieSearchHttpClient requestor;
	private Logger logger = LoggerFactory.getLogger(MovieSearchController.class);
	
	//TODO Rimuovere la logica da qui
	
	@RequestMapping(value="/search/movie", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Movie> searchMovieByTitle(String title, boolean withCrewDetails) throws IOException {
		logger.debug("BEGIN METHOD searchMovieByTitle");
		List<Movie> movies = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		String responseBody = requestor.searchByTitle(title);
		if (responseBody != null) {
			TmdbMovieDto tmdbMovieDto = mapper.readValue(responseBody, TmdbMovieDto.class);
			movies.addAll(tmdbMovieDto.getResults());
		} else logger.error("NULL RESPONSE BODY");
		if(withCrewDetails==true) {
			//TODO
		}
		logger.debug("END METHOD searchMovieByTitle");
		return movies;
	}
	
	@RequestMapping(value="/search/person", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Person> searchPerson(String personName) throws IOException {
		logger.debug("BEGIN METHOD searchPerson");
		ObjectMapper mapper = new ObjectMapper();
		String respBody = requestor.searchPerson(personName);
		List<Person> persons = new ArrayList<>();
		if (respBody != null) {
			TmdbPersonDto tmdbPersonDto = mapper.readValue(respBody, TmdbPersonDto.class);
			persons.addAll(tmdbPersonDto.getResults());
			persons = enrichPersonData(persons);
		} else logger.error("Null response body");
		logger.debug("END METHOD searchPerson");
		return persons;
	}
	
	private List<Person> enrichPersonData(List<Person> persons) throws JsonMappingException, JsonProcessingException {
		logger.debug("BEGIN METHOD enrichPersonData");
		ObjectMapper mapper = new ObjectMapper();
		List<Person> personsList = new ArrayList<Person>();
		for (Person p: persons) {
			String personResponseBody = requestor.searchPersonDetails(p.getPersonId());
			if(personResponseBody != null) {
				p = mapper.readValue(personResponseBody, Person.class);
				personsList.add(p);
			}
		}
		logger.debug("END METHOD enrichPersonData");
		return personsList;
	}
}
