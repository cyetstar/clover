package org.cyetstar.clover.service;

import java.util.Set;

import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.cyetstar.clover.entity.MovieCountry;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.clover.entity.MovieLanguage;
import org.cyetstar.clover.repository.CelebrityDao;
import org.cyetstar.clover.repository.MovieAkaDao;
import org.cyetstar.clover.repository.MovieCountryDao;
import org.cyetstar.clover.repository.MovieCreditDao;
import org.cyetstar.clover.repository.MovieDao;
import org.cyetstar.clover.repository.MovieGenreDao;
import org.cyetstar.clover.repository.MovieLanguageDao;
import org.cyetstar.clover.utils.DoubanRequest;
import org.cyetstar.clover.utils.JsonNodeParser;
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
		JsonNode jsonNode = doubanRequest.requestMovieInfo(doubanId);
		Movie movie = JsonNodeParser.toMovie(jsonNode);
		return mergeMovie(movie);
	}

	private Movie mergeMovie(Movie movie) {
		//判断doubanId是否存在
		String doubanId = movie.getDoubanId();
		Set<MovieAka> akas = movie.getAkas();
		Set<MovieCredit> directors = movie.getDirectors();
		Set<MovieCredit> casts = movie.getCasts();
		Set<MovieCredit> writers = movie.getWriters();
		Set<MovieCountry> countries = movie.getCountries();
		Set<MovieGenre> genres = movie.getGenres();
		Set<MovieLanguage> languages = movie.getLanguages();

		Movie oldMovie = movieDao.findByDoubanId(doubanId);
		if (oldMovie != null) {
			copyMovieProperties(movie, oldMovie);

			setDifferenceAka(movie, akas);
			saveDifferenceCountry(movie, countries);
			saveDifferenceGenre(movie, genres);
			saveDifferenceLanguage(movie, languages);

			saveDifferenceDirector(movie, directors);
			saveDifferenceCast(movie, casts);
			saveDifferenceWriter(movie, writers);

		} else {
			for (MovieAka aka : akas) {
				aka.setMovie(movie);
			}
			for (MovieCountry country : countries) {
				movie.getCountries().add(mergeMovieCountry(country));
			}
			for (MovieGenre genre : genres) {
				movie.getGenres().add(mergeMovieGenre(genre));
			}
			for (MovieLanguage language : languages) {
				movie.getLanguages().add(mergeMovieLanguage(language));
			}

			for (MovieCredit credit : directors) {
				mergeMovieCredit(credit);
				credit.setMovie(movie);
			}
			movie.getDirectors().addAll(directors);

			for (MovieCredit credit : casts) {
				mergeMovieCredit(credit);
				credit.setMovie(movie);
			}
			movie.getCasts().addAll(casts);

			for (MovieCredit credit : writers) {
				mergeMovieCredit(credit);
				credit.setMovie(movie);
			}
			movie.getWriters().addAll(writers);

		}
		return movieDao.save(movie);
	}

	private void saveDifferenceDirector(Movie movie, Set<MovieCredit> directors) {
		for (MovieCredit credit : directors) {
			try {
				mergeMovieCredit(credit);
			} catch (NullPointerException e) {
				continue;
			}
			credit.setMovie(movie);
		}
		Set<MovieCredit> difference = Sets.difference(directors, movie.getDirectors());
		movie.getDirectors().addAll(difference);
	}

	private void saveDifferenceCast(Movie movie, Set<MovieCredit> casts) {
		for (MovieCredit credit : casts) {
			try {
				mergeMovieCredit(credit);
			} catch (NullPointerException e) {
				continue;
			}
			credit.setMovie(movie);
		}
		Set<MovieCredit> difference = Sets.difference(casts, movie.getCasts());
		movie.getCasts().addAll(difference);
	}

	private void saveDifferenceWriter(Movie movie, Set<MovieCredit> writers) {
		for (MovieCredit credit : writers) {
			try {
				mergeMovieCredit(credit);
			} catch (NullPointerException e) {
				continue;
			}
			credit.setMovie(movie);
		}
		Set<MovieCredit> difference = Sets.difference(writers, movie.getWriters());
		movie.getWriters().addAll(difference);
	}

	private void setDifferenceAka(Movie movie, Set<MovieAka> akas) {
		for (MovieAka aka : akas) {
			aka.setMovie(movie);
		}
		Set<MovieAka> difference = Sets.difference(akas, movie.getAkas());
		movie.getAkas().addAll(difference);
	}

	private void saveDifferenceCountry(Movie movie, Set<MovieCountry> countries) {
		Set<MovieCountry> difference = Sets.difference(countries, movie.getCountries());
		for (MovieCountry country : difference) {
			movie.getCountries().add(mergeMovieCountry(country));
		}
	}

	private void saveDifferenceGenre(Movie movie, Set<MovieGenre> genres) {
		Set<MovieGenre> difference = Sets.difference(genres, movie.getGenres());
		for (MovieGenre genre : difference) {
			movie.getGenres().add(mergeMovieGenre(genre));
		}
	}

	private void saveDifferenceLanguage(Movie movie, Set<MovieLanguage> languages) {
		Set<MovieLanguage> difference = Sets.difference(languages, movie.getLanguages());
		for (MovieLanguage language : difference) {
			movie.getLanguages().add(mergeMovieLanguage(language));
		}
	}

	private void copyMovieProperties(Movie target, Movie source) {
		target.setId(source.getId());
		target.setImdb(source.getImdb());
		target.setImage(source.getImage());
		target.setCreatedAt(source.getCreatedAt());
		target.setUpdatedAt(source.getUpdatedAt());
		target.setAkas(source.getAkas());
		target.setDirectors(source.getDirectors());
		target.setCasts(source.getCasts());
		target.setWriters(source.getWriters());
		target.setCountries(source.getCountries());
		target.setGenres(source.getGenres());
		target.setLanguages(source.getLanguages());
	}

	private void mergeMovieCredit(MovieCredit credit) {
		if (credit.getCelebrity() == null || credit.getCelebrity().getDoubanId() == null) {
			throw new NullPointerException();
		}
		String doubanId = credit.getCelebrity().getDoubanId();
		Celebrity celebrity = celebrityDao.findByDoubanId(doubanId);
		if (celebrity == null) {
			celebrity = celebrityDao.save(credit.getCelebrity());
		}
		credit.setCelebrity(celebrity);
	}

	private MovieCountry mergeMovieCountry(MovieCountry country) {
		if (country.getValue() == null) {
			return null;
		}
		MovieCountry exist = movieCountryDao.findByValue(country.getValue());
		if (exist != null) {
			return exist;
		} else {
			return movieCountryDao.save(country);
		}
	}

	private MovieGenre mergeMovieGenre(MovieGenre genre) {
		if (genre.getValue() == null) {
			return null;
		}
		MovieGenre exist = movieGenreDao.findByValue(genre.getValue());
		if (exist != null) {
			return exist;
		} else {
			return movieGenreDao.save(genre);
		}
	}

	private MovieLanguage mergeMovieLanguage(MovieLanguage language) {
		if (language.getValue() == null) {
			return null;
		}
		MovieLanguage exist = movieLanguageDao.findByValue(language.getValue());
		if (exist != null) {
			return exist;
		} else {
			return movieLanguageDao.save(language);
		}
	}

}
