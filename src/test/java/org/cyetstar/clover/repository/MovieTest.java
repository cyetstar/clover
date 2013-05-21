package org.cyetstar.clover.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.cyetstar.clover.data.MovieData;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.code.domain.Clause;
import org.cyetstar.code.domain.Fetch;
import org.cyetstar.code.spring.SpecificationCreater;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.common.collect.Lists;

@ActiveProfiles("development")
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
	public void copy() {

		Movie old = dao.findByDoubanId("1291560");

	}

	//@Test
	public void find1() {
		//		Clause clause1 = new Clause(ClauseItem.like("akas.title", "abc")).add(
		//				new ClauseItem("year", Operator.EQ, "1980")).disjunction();
		Clause clause1 = Clause.instance().like("akas.title", "abc").eq("year", "1980").disjunction();
		Clause clause2 = Clause.instance().eq("imdb", "tt2817211").gt("rating", 9.0).disjunction();

		//		new Clause(new ClauseItem("imdb", Operator.EQ, "tt2817211")).add(new ClauseItem("rating", Operator.GT, "9.0"))
		//				.disjunction();
		List<Clause> clauses = Lists.newArrayList(clause1, clause2);
		Specification<Movie> spec = SpecificationCreater.searchWith(new Fetch("directors.celebrity"), new Fetch(
				"casts.celebrity"), new Fetch("akas"));
		dao.findAll(spec, new PageRequest(0, 10, new Sort(Direction.ASC, "createdAt")));

	}

	//@Test
	public void create() {
		Movie movie = MovieData.newMovie("abc");
		dao.save(movie);
	}

	//@Test
	public void updateByFind() {
		Movie movie = dao.findOne(1L);
		movie.setTitle("ccc");
		movie.setYear("1980");
		movie.setSubtype("movie");
		movie.setCreatedAt(DateTime.now());
		dao.save(movie);
	}

	//@Test
	public void updateBySet() {
		Movie movie = new Movie(1L);
		movie.setTitle("ccc");
		movie.setYear("1980");
		movie.setSubtype("movie");
		movie.setCreatedAt(DateTime.now());
		dao.save(movie);
	}

	//@Test
	public void setGenre() {
		MovieGenre genre = genreDao.findOne(1L);
		Movie movie = dao.findOne(1L);
		movie.getGenres().add(genre);
		dao.save(movie);
	}

	//@Test
	public void genreSave() {
		MovieGenre genre = genreDao.findOne(2L);
		Movie movie = dao.findOne(1L);
		genre.getMovies().add(movie);
		genreDao.save(genre);
	}

	//@Test
	public void creditSave() {
		MovieCredit credit = new MovieCredit();
		credit.setCelebrity(new Celebrity(1L));
		credit.setMovie(new Movie(1L));
		credit.setRole("cast");
		creditDao.save(credit);
	}

}
