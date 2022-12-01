package com.ray.service;

import java.util.List;

import com.ray.dao.MovieDAO;
import com.ray.entity.Movie;

public class MovieService {
	private MovieDAO movieDAO;
	
	public MovieService() {
		movieDAO = new MovieDAO();
	}
	
	
	public List<Movie> listMovie() {
		return movieDAO.getListAll();
	}
	
	public Movie getById(Integer movieId) {
		return movieDAO.getById(movieId);
	}
	
	public void delete(Integer movieId) {
		movieDAO.deleteById(movieId);
	}
	
	
	public String create(Movie movie) {
		Movie existMovie = movieDAO.getByName(movie.getName());
		
		if (existMovie != null) {
			return "The Movie name already exists";
		}
		
		movieDAO.create(movie);
		return null;
	}
	
	
	public String update(Movie movie) {
		Movie existMovie = movieDAO.getByNameAndNotMovieId(movie);
		
		if (existMovie != null) {
			return "The Movie name already exists";
		}
		
		movieDAO.update(movie);
		return null;
	}
	
	
	public List<Movie> searchMovieByName(String name) {
		return movieDAO.getMoviesByName(name);
	}
}
