package org.cyetstar.clover.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.Validate;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.cyetstar.clover.entity.MovieCountry;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.clover.entity.MovieLanguage;
import org.cyetstar.clover.repository.CelebrityDao;
import org.cyetstar.clover.repository.JpaSpecRepository;
import org.cyetstar.clover.repository.MovieAkaDao;
import org.cyetstar.clover.repository.MovieCountryDao;
import org.cyetstar.clover.repository.MovieCreditDao;
import org.cyetstar.clover.repository.MovieDao;
import org.cyetstar.clover.repository.MovieGenreDao;
import org.cyetstar.clover.repository.MovieLanguageDao;
import org.cyetstar.clover.utils.DoubanParser;
import org.cyetstar.clover.utils.DoubanRequest;
import org.cyetstar.core.domain.Clause;
import org.cyetstar.core.domain.Fetch;
import org.cyetstar.core.spring.DataDomainHelper;
import org.cyetstar.core.spring.SpecificationCreater;
import org.cyetstar.utils.Reflections;
import org.cyetstar.utils.Strings;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Sets;

@Service
public class MovieService {

	@Autowired
	MovieDao movieDao;

	@Autowired
	MovieAkaDao movieAkaDao;

	@Autowired
	MovieCountryDao movieCountryDao;

	@Autowired
	MovieGenreDao movieGenreDao;

	@Autowired
	MovieLanguageDao movieLanguageDao;

	@Autowired
	MovieCreditDao movieCreditDao;

	@Autowired
	CelebrityDao celebrityDao;

	@Autowired
	DoubanRequest doubanRequest;

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
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
		JsonNode jsonNode = doubanRequest.requestMovieInfo(doubanId);
		Movie movie = DoubanParser.toMovie(jsonNode);
		return mergeMovie(movie);
	}

	public Celebrity requestCelebrity(String doubanId) {
		JsonNode jsonNode = doubanRequest.requestCelebrityInfo(doubanId);
		Celebrity celebrity = DoubanParser.toCelebrity(jsonNode);
		return mergeCelebrity(celebrity);
	}

	private Movie mergeMovie(Movie movie) {
		String doubanId = movie.getDoubanId();
		Movie persistentMovie = movieDao.findByDoubanId(doubanId);
		if (persistentMovie != null) {
			mergeMovieProperties(movie, persistentMovie);
		} else {
			saveMovieProperties(movie);
		}
		return movieDao.save(movie);
	}

	private Celebrity mergeCelebrity(Celebrity celebrity) {
		String doubanId = celebrity.getDoubanId();
		Celebrity persistentCelebrity = celebrityDao.findByDoubanId(doubanId);
		if (persistentCelebrity != null) {
			celebrity.setId(persistentCelebrity.getId());
			celebrity.setAvatar(persistentCelebrity.getAvatar());
			celebrity.setCreatedAt(persistentCelebrity.getCreatedAt());
			celebrity.setUpdatedAt(DateTime.now());
		} else {
			celebrity.setCreatedAt(DateTime.now());
		}
		return celebrityDao.save(celebrity);
	}

	///////////////////////////

	private void saveMovieProperties(Movie movie) {
		movie.setCreatedAt(DateTime.now());

		for (MovieAka aka : movie.getAkas()) {
			aka.setMovie(movie);
		}
		mergeSimpleEntitySet(movie.getCountries(), movieCountryDao);
		mergeSimpleEntitySet(movie.getGenres(), movieGenreDao);
		mergeSimpleEntitySet(movie.getLanguages(), movieLanguageDao);

		mergeMovieCreditSet(movie.getDirectors(), movie);
		mergeMovieCreditSet(movie.getCasts(), movie);
		mergeMovieCreditSet(movie.getWriters(), movie);
	}

	private void mergeMovieProperties(Movie movie, Movie persistentMovie) {
		//simply property copy from persist
		movie.setId(persistentMovie.getId());
		movie.setImdb(persistentMovie.getImdb());
		movie.setImage(persistentMovie.getImage());
		movie.setCreatedAt(persistentMovie.getCreatedAt());
		movie.setUpdatedAt(DateTime.now());

		setDifferenceAka(movie, persistentMovie);
		saveDifferenceCountry(movie, persistentMovie);
		saveDifferenceGenre(movie, persistentMovie);
		saveDifferenceLanguage(movie, persistentMovie);

		saveDifferenceDirector(movie, persistentMovie);
		saveDifferenceCast(movie, persistentMovie);
		saveDifferenceWriter(movie, persistentMovie);
	}

	private void setDifferenceAka(Movie movie, Movie persistentMovie) {
		Set<MovieAka> difference = Sets.difference(movie.getAkas(), persistentMovie.getAkas());
		movie.setAkas(persistentMovie.getAkas());
		movie.addAllAka(difference);
	}

	private void saveDifferenceCountry(Movie movie, Movie persistentMovie) {
		Set<MovieCountry> difference = saveDifferenceEntitySet(movie.getCountries(), persistentMovie.getCountries(),
				movieCountryDao);
		movie.setCountries(persistentMovie.getCountries());
		movie.getCountries().addAll(difference);
	}

	private void saveDifferenceGenre(Movie movie, Movie persistentMovie) {
		Set<MovieGenre> difference = saveDifferenceEntitySet(movie.getGenres(), persistentMovie.getGenres(),
				movieGenreDao);
		movie.setGenres(persistentMovie.getGenres());
		movie.getGenres().addAll(difference);
	}

	private void saveDifferenceLanguage(Movie movie, Movie persistentMovie) {
		Set<MovieLanguage> difference = saveDifferenceEntitySet(movie.getLanguages(), persistentMovie.getLanguages(),
				movieLanguageDao);
		movie.setLanguages(persistentMovie.getLanguages());
		movie.getLanguages().addAll(difference);
	}

	private void saveDifferenceDirector(Movie movie, Movie persistentMovie) {
		Set<MovieCredit> directors = movie.getDirectors();
		mergeMovieCreditSet(directors, movie);
		Set<MovieCredit> difference = Sets.difference(directors, persistentMovie.getDirectors());
		movie.setDirectors(persistentMovie.getDirectors());
		movie.getDirectors().addAll(difference);
	}

	private void saveDifferenceCast(Movie movie, Movie persistentMovie) {
		Set<MovieCredit> casts = movie.getCasts();
		mergeMovieCreditSet(casts, movie);
		Set<MovieCredit> difference = Sets.difference(casts, persistentMovie.getDirectors());
		movie.setCasts(persistentMovie.getCasts());
		movie.getCasts().addAll(difference);
	}

	private void saveDifferenceWriter(Movie movie, Movie persistentMovie) {
		Set<MovieCredit> writers = movie.getWriters();
		mergeMovieCreditSet(writers, movie);
		Set<MovieCredit> difference = Sets.difference(writers, persistentMovie.getDirectors());
		movie.setWriters(persistentMovie.getWriters());
		movie.getWriters().addAll(difference);
	}

	private void mergeMovieCreditSet(Set<MovieCredit> set, Movie movie) {
		for (Iterator<MovieCredit> iter = set.iterator(); iter.hasNext();) {
			try {
				MovieCredit credit = iter.next();
				mergeMovieCredit(credit);
				credit.setMovie(movie);
			} catch (NullPointerException e) {
				iter.remove();
			}
		}
	}

	private void mergeMovieCredit(MovieCredit credit) {
		Validate.notNull(credit.getCelebrity());
		Validate.notNull(credit.getCelebrity().getDoubanId());
		String doubanId = credit.getCelebrity().getDoubanId();
		Celebrity celebrity = celebrityDao.findByDoubanId(doubanId);
		if (celebrity == null) {
			celebrity = credit.getCelebrity();
			celebrity.setCreatedAt(DateTime.now());
			celebrity = celebrityDao.save(celebrity);
		}
		credit.setCelebrity(celebrity);
	}

	//////////////////

	private <T, ID extends Serializable> Set<T> saveDifferenceEntitySet(Set<T> set, Set<T> persistSet,
			JpaSpecRepository<T, ID> repository) {
		Set<T> difference = Sets.difference(set, persistSet);
		difference = mergeSimpleEntitySet(difference, repository);
		return difference;
	}

	//遍历集合元素，在数据库查询，如果存在，则返回已存在，如果不存在，则保存后返回。
	private <T, ID extends Serializable> Set<T> mergeSimpleEntitySet(Set<T> set, JpaSpecRepository<T, ID> repository) {
		for (T entity : set) {
			try {
				T mergeEntity = mergeSimpleEntity(entity, "value", repository);
				BeanUtils.copyProperties(entity, mergeEntity);
			} catch (Exception e) {
				throw Reflections.convertReflectionExceptionToUnchecked(e);
			}
		}
		return set;
	}

	private <T, ID extends Serializable> T mergeSimpleEntity(T entity, String propertyName,
			JpaSpecRepository<T, ID> repository) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String value = BeanUtils.getProperty(entity, propertyName);
		Validate.notNull(value);
		Specification<T> spec = SpecificationCreater.searchBy(Clause.instance().eq(propertyName, value));
		T exist = repository.findOne(spec);
		return exist != null ? exist : repository.save(entity);
	}

}
