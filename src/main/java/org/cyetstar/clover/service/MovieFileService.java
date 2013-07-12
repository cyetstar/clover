package org.cyetstar.clover.service;

import java.util.List;

import org.cyetstar.clover.entity.MovieFile;
import org.cyetstar.clover.repository.JpaSpecRepository;
import org.cyetstar.clover.repository.MovieFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieFileService extends BaseService<MovieFile, Long> {

	@Autowired
	MovieFileDao fileDao;

	@Override
	protected JpaSpecRepository<MovieFile, Long> getRepository() {
		return fileDao;
	}

	public List<MovieFile> findByMovieId(Long movieId) {
		return fileDao.findByMovieId(movieId);
	}

}
