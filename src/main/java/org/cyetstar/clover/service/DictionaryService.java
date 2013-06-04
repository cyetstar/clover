package org.cyetstar.clover.service;

import java.util.List;

import org.cyetstar.clover.entity.MovieCountry;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.clover.entity.MovieLanguage;
import org.cyetstar.clover.repository.MovieCountryDao;
import org.cyetstar.clover.repository.MovieGenreDao;
import org.cyetstar.clover.repository.MovieLanguageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

	@Autowired
	MovieGenreDao movieGenreDao;

	@Autowired
	MovieCountryDao movieCountryDao;

	@Autowired
	MovieLanguageDao movieLanguageDao;

	public List<MovieGenre> findAllMovieGenre() {
		return movieGenreDao.findAll();
	}

	public List<MovieCountry> findAllMovieCountry() {
		return movieCountryDao.findAll();
	}

	public List<MovieLanguage> findAllMovieLanguage() {
		return movieLanguageDao.findAll();
	}

}
