package org.cyetstar.clover.service;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.repository.MovieDao;
import org.cyetstar.utils.SortParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	MovieDao movieDao;

	public Page<Movie> findMovie(String key, int page, int size, String sort) {
		return movieDao.findAll(new PageRequest(page, size, SortParser.parse(sort)));
	}

	public Movie findMovie(Long id) {
		return movieDao.findOne(id);
	}

	public void saveMovie(Movie movie) {
		movieDao.save(movie);
	}

	public void deleteMovie(Long id) {
		movieDao.delete(id);
	}

}
