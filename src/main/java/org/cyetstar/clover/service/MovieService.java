package org.cyetstar.clover.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.Validate;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.repository.CelebrityDao;
import org.cyetstar.clover.repository.JpaSpecRepository;
import org.cyetstar.clover.repository.MovieAkaDao;
import org.cyetstar.clover.repository.MovieCountryDao;
import org.cyetstar.clover.repository.MovieCreditDao;
import org.cyetstar.clover.repository.MovieDao;
import org.cyetstar.clover.repository.MovieGenreDao;
import org.cyetstar.clover.repository.MovieLanguageDao;
import org.cyetstar.clover.rest.douban.DoubanRequestClient;
import org.cyetstar.core.domain.Clause;
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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

@Service
@Transactional
public class MovieService {

	public static final String POSTER_ORIGIN_PATH = "static/poster/origin/";

	public static final String POSTER_SMALL_PATH = "static/poster/small/";

	public static final String SUFFIX = ".jpg";

	public static final Map<String, Integer> POSTER_SMALL_SIZE = ImmutableMap.of("width", 120, "height", 160);

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
	DoubanRequestClient doubanRequestClient;

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
	public Movie findMovie(Specification<Movie> spec) {
		return movieDao.findOne(spec);
	}

	@Transactional(readOnly = true)
	public Movie findMovie(Long id) {
		return movieDao.findOne(id);
	}

	public void saveMovie(Movie movie) {
		movieDao.save(movie);
	}

	public void deleteMovie(Long id) {
		movieDao.delete(id);
	}

	public Movie fetchMovie(String doubanId) {
		Movie movie = doubanRequestClient.requestMovie(doubanId);
		return saveOrUpdateMovie(movie);
	}

	public Movie updateMoviePoster(Long id, String posterFilename) {
		Movie movie = movieDao.findOne(id);
		movie.setImage(posterFilename);
		movie.setUpdatedAt(DateTime.now());
		return movieDao.save(movie);
	}

	public Movie updateMovieRating(Long id, String doubanId) {
		Movie movie = doubanRequestClient.requestMovie(doubanId);
		movieDao.updateRating(movie.getRating(), movie.getNumRaters(), DateTime.now(), id);
		return movie;
	}

	public Celebrity requestCelebrity(String doubanId) {
		Celebrity celebrity = doubanRequestClient.requestCelebrity(doubanId);
		return mergeCelebrity(celebrity);
	}

	private Movie saveOrUpdateMovie(Movie movie) {
		String doubanId = movie.getDoubanId();
		Movie persistentMovie = movieDao.findByDoubanId(doubanId);
		if (persistentMovie != null) {
			// mergeMovieProperties(movie, persistentMovie);
		} else {
			movie = persistMovieProperties(movie);
			movie.setCreatedAt(DateTime.now());
			for (MovieAka aka : movie.getAkas()) {
				aka.setMovie(movie);
			}
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

	// /////////////////////////

	private Movie persistMovieProperties(Movie movie) {
		persistSimpleEntityCollection(movie.getCountries(), "value", movieCountryDao);
		persistSimpleEntityCollection(movie.getGenres(), "value", movieGenreDao);
		persistSimpleEntityCollection(movie.getLanguages(), "value", movieLanguageDao);

		persistMovieCreditCollection(movie.getDirectors(), movie, MovieCredit.DIRECTOR);
		persistMovieCreditCollection(movie.getCasts(), movie, MovieCredit.CAST);
		persistMovieCreditCollection(movie.getWriters(), movie, MovieCredit.WRITER);
		return movie;
	}

	// private void mergeMovieProperties(Movie movie, Movie persistentMovie) {
	// // simply property copy from persist
	// movie.setId(persistentMovie.getId());
	// movie.setImdb(persistentMovie.getImdb());
	// movie.setImage(persistentMovie.getImage());
	// movie.setCreatedAt(persistentMovie.getCreatedAt());
	// movie.setUpdatedAt(DateTime.now());
	//
	// setDifferenceAka(movie, persistentMovie);
	// saveDifferenceCountry(movie, persistentMovie);
	// saveDifferenceGenre(movie, persistentMovie);
	// saveDifferenceLanguage(movie, persistentMovie);
	//
	// saveDifferenceDirector(movie, persistentMovie);
	// saveDifferenceCast(movie, persistentMovie);
	// saveDifferenceWriter(movie, persistentMovie);
	// }

	private void setDifferenceAka(Movie movie, Movie persistentMovie) {
		Set<MovieAka> difference = Sets.difference(movie.getAkas(), persistentMovie.getAkas());
		movie.setAkas(persistentMovie.getAkas());
		movie.addAllAka(difference);
	}

	// private void saveDifferenceCountry(Movie movie, Movie persistentMovie) {
	// Set<MovieCountry> difference = saveDifferenceDictSet(movie.getCountries(), persistentMovie.getCountries(),
	// movieCountryDao);
	// movie.setCountries(persistentMovie.getCountries());
	// movie.getCountries().addAll(difference);
	// }
	//
	// private void saveDifferenceGenre(Movie movie, Movie persistentMovie) {
	// Set<MovieGenre> difference = saveDifferenceDictSet(movie.getGenres(), persistentMovie.getGenres(), movieGenreDao);
	// movie.setGenres(persistentMovie.getGenres());
	// movie.getGenres().addAll(difference);
	// }
	//
	// private void saveDifferenceLanguage(Movie movie, Movie persistentMovie) {
	// Set<MovieLanguage> difference = saveDifferenceDictSet(movie.getLanguages(), persistentMovie.getLanguages(),
	// movieLanguageDao);
	// movie.setLanguages(persistentMovie.getLanguages());
	// movie.getLanguages().addAll(difference);
	// }

	// private void saveDifferenceDirector(Movie movie, Movie persistentMovie) {
	// Set<MovieCredit> directors = movie.getDirectors();
	// mergeCreditSet(directors, movie);
	// Set<MovieCredit> difference = Sets.difference(directors, persistentMovie.getDirectors());
	// movie.setDirectors(persistentMovie.getDirectors());
	// movie.getDirectors().addAll(difference);
	// }
	//
	// private void saveDifferenceCast(Movie movie, Movie persistentMovie) {
	// Set<MovieCredit> casts = movie.getCasts();
	// mergeCreditSet(casts, movie);
	// Set<MovieCredit> difference = Sets.difference(casts, persistentMovie.getDirectors());
	// movie.setCasts(persistentMovie.getCasts());
	// movie.getCasts().addAll(difference);
	// }
	//
	// private void saveDifferenceWriter(Movie movie, Movie persistentMovie) {
	// Set<MovieCredit> writers = movie.getWriters();
	// mergeCreditSet(writers, movie);
	// Set<MovieCredit> difference = Sets.difference(writers, persistentMovie.getDirectors());
	// movie.setWriters(persistentMovie.getWriters());
	// movie.getWriters().addAll(difference);
	// }

	private void persistMovieCreditCollection(Collection<MovieCredit> collection, Movie movie, String role) {
		if (collection != null) {
			for (MovieCredit credit : collection) {
				credit.setMovie(movie);
				credit.setRole(role);
				persistMovieCredit(credit);
			}
		}
	}

	/**
	 * 持久化MovieCredit的Celebrity
	 * 
	 * @param collection
	 * @param movie
	 */
	private void persistMovieCredit(MovieCredit credit) {
		try {
			String doubanId = credit.getCelebrity().getDoubanId();
			Celebrity celebrity = celebrityDao.findByDoubanId(doubanId);
			if (celebrity == null) {
				celebrity = credit.getCelebrity();
				celebrity.setCreatedAt(DateTime.now());
				celebrity = celebrityDao.save(celebrity);
			}
			credit.setCelebrity(celebrity);
		} catch (NullPointerException e) {
			return;
		}
	}

	// ////////////////

	// private <T, ID extends Serializable> Set<T> saveDifferenceDictSet(Set<T> set, Set<T> persistSet,
	// JpaSpecRepository<T, ID> repository) {
	// Set<T> difference = Sets.difference(set, persistSet);
	// difference = mergeDictSet(difference, repository);
	// return difference;
	// }

	/**
	 * 遍历集合元素，根据persistKey判断是否已经持久化，如果没有则持久化。
	 * 
	 * @param collection
	 * @param persistKey
	 * @param repository
	 */
	private <T, ID extends Serializable> void persistSimpleEntityCollection(Collection<T> collection, String persistKey,
			JpaSpecRepository<T, ID> repository) {
		if (collection != null) {
			for (T entity : collection) {
				persistSimpleEntity(entity, persistKey, repository);
			}
		}
	}

	private <T, ID extends Serializable> void persistSimpleEntity(T entity, String persistKey,
			JpaSpecRepository<T, ID> repository) {
		try {
			String value = BeanUtils.getProperty(entity, persistKey);
			Validate.notNull(value);
			Specification<T> spec = SpecificationCreater.searchBy(Clause.instance().eq(persistKey, value));
			T exist = repository.findOne(spec);
			if (exist != null) {
				BeanUtils.copyProperties(entity, exist);
			} else {
				entity = repository.save(entity);
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}
	}

}
