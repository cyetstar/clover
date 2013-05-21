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

	@Test
	public void requestDouban() {
		movieService.requestMovie("3231742");
	}

}
