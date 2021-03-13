package com.salvatore.cinemates.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salvatore.cinemates.client.MovieSearchHttpClient;
import com.salvatore.cinemates.dto.TmdbKeywordsDto;
import com.salvatore.cinemates.dto.TmdbMovieCastDto;
import com.salvatore.cinemates.dto.TmdbMovieDto;
import com.salvatore.cinemates.dto.TmdbPersonDto;
import com.salvatore.cinemates.model.Keyword;
import com.salvatore.cinemates.model.Movie;
import com.salvatore.cinemates.model.Person;

@Service
public class MovieSearchService {
	@Autowired
	private MovieSearchHttpClient httpClient;
	private Logger logger = LoggerFactory.getLogger(MovieSearchService.class);
	@Autowired
	private ObjectMapper mapper;
	
	@Bean
	public ObjectMapper createMapper() {
		return new ObjectMapper();
	}
	
	public List<Movie> getMoviesByTitle(String query) {
		logger.info("BEGIN METHOD getMoviesByTitle");
		List<Movie> movies = new ArrayList<>();
		String responseBody = httpClient.searchByTitle(query);
		
		try {
			TmdbMovieDto dto = mapper.readValue(responseBody, TmdbMovieDto.class);
			movies.addAll(dto.getResults());
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		logger.info("END METHOD getMoviesByTitle");
		return movies;
	}
	
	public Movie enrichMovieWithCrewDetails(Movie movie) {
		logger.info("BEGIN METHOD enrichMovieWithCrewDetails");
		String responseBody = httpClient.retrieveCastDetailsForMovie(movie.getTmdbId());
		
		try {
			TmdbMovieCastDto dto = mapper.readValue(responseBody, TmdbMovieCastDto.class);
			Collections.sort(dto.getCast(), Collections.reverseOrder(new Person.PopularityComparator()));
			Collections.sort(dto.getCrew(), Collections.reverseOrder(new Person.PopularityComparator()));
			for (Person p: dto.getCast()) {
				if (p.getPopularity() >= 4 && p.getKnownForDepartment().equals("Acting")) {
					movie.addActor(p);
				}
			}
			for (Person p: dto.getCrew()) {
				if (p.getJob().equals("Director")) {
					movie.addDirector(p);
				} else if (p.getJob().equals("Producer") && p.getPopularity() >= 4) {
					movie.addProducer(p);
				}
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		logger.info("END METHOD enrichMovieWithCrewDetails");
		return movie;
	}
	
	public Movie getMovieDetails(int movieId) {
		logger.info("BEGIN METHOD getMovieDetails");
		Movie movie = new Movie();
		String responseBody = httpClient.retrieveMovieDetails(movieId);
		
		try {
			movie = mapper.readValue(responseBody, Movie.class);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		movie = enrichMovieWithCrewDetails(movie);
		movie.setKeywords(getKeywordsForMovie(movie));
		logger.info("END METHOD getMovieDetails");
		return movie;
	}
	
	public List<Keyword> getKeywordsForMovie(Movie m) {
		logger.info("BEGIN METHOD getKeywordsForMovie");
		List<Keyword> keywords = new ArrayList<>();
		String responseBody = httpClient.getKeywordsForMovieId(m.getTmdbId());
		
		try {
			TmdbKeywordsDto dto = mapper.readValue(responseBody, TmdbKeywordsDto.class);
			keywords.addAll(dto.getKeywords());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} 
		logger.info("END METHOD getKeywordsForMovie");
		return keywords;
	}
	
	//TODO: Rimuovere questo metodo
	/*
	private List<Person> enrichActorsList(List<Person> actors) {
		logger.info("BEGIN METHOD enrichActorsList");
		List<Person> persons = new ArrayList<>();
		for (Person p: actors) {
			persons.add(enrichSinglePersonData(p));
		}
		logger.info("END METHOD enrichActorsList");
		return persons;
	}
	*/
	
	public List<Person> getPersons(String query) {
		logger.info("BEGIN METHOD getPersons");
		List<Person> persons = new ArrayList<>();
		String responseBody = httpClient.searchPerson(query);
		
		try {
			TmdbPersonDto dto = mapper.readValue(responseBody, TmdbPersonDto.class);
			persons.addAll(dto.getResults());
			for (Person p: persons) {
				p = enrichSinglePersonData(p);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		logger.info("END METHOD getPersons");
		return persons;
	}
	
	public Person enrichSinglePersonData(Person person) {
		logger.info("BEGIN METHOD enrichSinglePersonData");
		Person enriched = new Person();
		String responseBody = httpClient.searchPersonDetails(person.getPersonId());	
		try {
			enriched = mapper.readValue(responseBody, Person.class);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		logger.info("END METHOD enrichSinglePersonData");
		return enriched;
	}
}
