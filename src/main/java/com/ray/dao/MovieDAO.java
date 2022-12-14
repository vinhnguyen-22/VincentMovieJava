package com.ray.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ray.entity.Movie;

public class MovieDAO extends JpaDAO<Movie> {

	public MovieDAO() {
		// TODO Auto-generated constructor stub
		super(Movie.class);
	}

	@Override
	public Movie create(Movie object) {
		// TODO Auto-generated method stub
		return super.create(object);
	}

	@Override
	public Movie update(Movie object) {
		// TODO Auto-generated method stub
		return super.update(object);
	}

	@Override
	public Movie getById(Object objectId) {
		// TODO Auto-generated method stub
		return super.getById(objectId);
	}

	@Override
	public List<Movie> getListAll() {
		// TODO Auto-generated method stub
		return super.getListAll();
	}

	@Override
	public void deleteById(Object objectId) {
		// TODO Auto-generated method stub
		super.deleteById(objectId);
	}

	@Override
	public long getTotalRecord() {
		// TODO Auto-generated method stub
		return super.getTotalRecord();
	}

	public Movie getByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);

		List<Movie> movieList = super.getNamedEqueryWithParams("Movie.HQL.getByName", params);

		/// get first record
		if (movieList != null && movieList.size() > 0) {
			return movieList.get(0);
		}

		return null;
	}

	public Movie getByNameAndNotMovieId(Movie movie) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", movie.getName());
		params.put("movieId", movie.getMovieId());

		List<Movie> movieList = super.getNamedEqueryWithParams("Movie.HQL.getByNameAndNotMovieId", params);

		/// get first record
		if (movieList != null && movieList.size() > 0) {
			return movieList.get(0);
		}

		return null;
	}

	public List<Movie> getMoviesByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);

		List<Movie> movieList = super.getNamedEqueryWithParams("Movie.HQL.getByName", params);

		return movieList;
	}

}
