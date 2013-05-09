package org.cyetstar.clover.service;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.repository.mybatis.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	MovieMapper movieMapper;

	public Movie findMovie(Long id) {
		return movieMapper.find(id);
	}

}
