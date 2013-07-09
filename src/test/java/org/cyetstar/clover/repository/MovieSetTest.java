package org.cyetstar.clover.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class MovieSetTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	MovieSetDao movieSetDao;

	@Test
	public void find() {
		movieSetDao.findAddInSets(1L);
	}

}
