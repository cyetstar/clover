package org.cyetstar.clover.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.cyetstar.clover.data.MovieData;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieGenre;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class MovieTest extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	EntityManager em;

	@Autowired
	MovieDao dao;

	@Autowired
	MovieGenreDao genreDao;

	@Autowired
	MovieCreditDao creditDao;

	@Test
	public void create() {
		Movie movie = MovieData.newMovie("abc");
		movie.addAka(MovieData.newMovieAka("tt"));
		dao.save(movie);
	}

	@Test
	public void updateByFind() {
		Movie movie = dao.findOne(1L);
		movie.addAka(MovieData.newMovieAka("c3"));
		movie.setTitle("ccc");
		movie.setYear("1980");
		movie.setSubtype("movie");
		movie.setCreatedAt(DateTime.now());
		dao.save(movie);
	}

	@Test
	public void updateBySet() {
		Movie movie = new Movie(1L);
		movie.addAka(MovieData.newMovieAka("c3"));
		movie.setTitle("ccc");
		movie.setYear("1980");
		movie.setSubtype("movie");
		movie.setCreatedAt(DateTime.now());
		dao.save(movie);
	}

	@Test
	public void setGenre() {
		MovieGenre genre = genreDao.findOne(1L);
		Movie movie = dao.findOne(1L);
		movie.getGenres().add(genre);
		dao.save(movie);
	}

	@Test
	public void genreSave() {
		MovieGenre genre = genreDao.findOne(2L);
		Movie movie = dao.findOne(1L);
		genre.getMovies().add(movie);
		genreDao.save(genre);
	}

	@Test
	public void creditSave() {
		MovieCredit credit = new MovieCredit();
		credit.setCelebrity(new Celebrity(1L));
		credit.setMovie(new Movie(1L));
		credit.setRole("cast");
		creditDao.save(credit);
	}

}
