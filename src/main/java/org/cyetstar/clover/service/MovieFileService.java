package org.cyetstar.clover.service;

import java.util.List;

import org.cyetstar.clover.entity.MovieFile;
import org.cyetstar.clover.repository.MovieFileDao;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieFileService {

	@Autowired
	MovieFileDao fileDao;

	public MovieFile createFile(MovieFile file) {
		file.setCreatedAt(DateTime.now());
		return fileDao.save(file);
	}

	public MovieFile updateFile(MovieFile file) {
		file.setUpdatedAt(DateTime.now());
		return fileDao.save(file);
	}

	public void deleteFile(Long id) {
		fileDao.delete(id);
	}

	public List<MovieFile> findFilesByMovieId(Long movieId) {
		return fileDao.findByMovieId(movieId);
	}

	public MovieFile findFile(Long id) {
		return fileDao.findOne(id);
	}

}
