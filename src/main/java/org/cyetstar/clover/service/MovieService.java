package org.cyetstar.clover.service;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.repository.MovieDao;
import org.cyetstar.code.domain.Clause;
import org.cyetstar.code.domain.Fetch;
import org.cyetstar.code.spring.DataDomainHelper;
import org.cyetstar.code.spring.SpecificationCreater;
import org.cyetstar.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	MovieDao movieDao;

	public Page<Movie> findMovie(String key, int pageNum, int pageSize, String sort) {
		Pageable pageable = new PageRequest(pageNum, pageSize, DataDomainHelper.parseSort(sort.split(",")));
		if (key != null) {
			Movie movie = null;
			if (Strings.isInteger(key)) {
				movie = movieDao.findByDoubanId(key);
			} else if (key.startsWith(Movie.IMDB_PREFIX)) {
				movie = movieDao.findByImdb(key);
			} else {
				Specification<Movie> spec = SpecificationCreater.searchByWith(Clause.instance().like("title", key)
						.like("akas.title", key).disjunction());
				return movieDao.findAll(spec, pageable);
			}
			return DataDomainHelper.PageWrapper(movie, pageable);
		} else {
			return movieDao.findAll(pageable);
		}
	}

	public Movie findMovie(Long id) {
		Specification<Movie> spec = SpecificationCreater.searchByWith(Clause.instance().eq("id", id), new Fetch(
				"genres"), new Fetch("akas"), new Fetch("countries"), new Fetch("languages"), new Fetch(
				"directors.celebrity"), new Fetch("casts.celebrity"), new Fetch("writers.celebrity"));
		return movieDao.findOne(spec);
	}

	public void saveMovie(Movie movie) {
		movieDao.save(movie);
	}

	public void deleteMovie(Long id) {
		movieDao.delete(id);
	}

	public Movie requestMovie(String doubanId) {

		return null;
	}

}
