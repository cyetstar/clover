package org.cyetstar.clover.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class MovieServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	MovieService movieService;

	Long id;

	//@Before
	public void beforeRequestDouban() {
		id = movieService.requestMovie("3231742").getId();
	}

	@Test
	public void requestDouban() {
		id = movieService.requestMovie("3231823").getId();

		//		Movie movie = movieService.findMovie(id);
		//
		//		assertEquals(3, movie.getAkas().size());
		//		assertEquals(1, movie.getDirectors().size());
		//		assertEquals(4, movie.getCasts().size());
		//		assertEquals(2, movie.getGenres().size());
		//		assertEquals(2, movie.getCountries().size());

	}

}
