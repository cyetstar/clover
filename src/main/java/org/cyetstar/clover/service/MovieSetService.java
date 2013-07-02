package org.cyetstar.clover.service;

import java.util.List;

import org.cyetstar.clover.entity.MovieSet;
import org.cyetstar.clover.repository.MovieSetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieSetService {

	@Autowired
	MovieSetDao setDao;

	public Page<MovieSet> findUnAddInSets(Long movieId, int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize);
		return setDao.findUnAddInSets(movieId, pageable);
	}

	public List<MovieSet> findSetsByMovieId(Long movieId) {
		return setDao.findAddInSets(movieId);
	}

	public MovieSet saveMovieSet(MovieSet movieSet) {
		return setDao.save(movieSet);
	}

}
