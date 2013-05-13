package org.cyetstar.clover.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class JpaMappingTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(JpaMappingTest.class);

	@PersistenceContext
	private EntityManager em;

	@Test
	public void allClassMapping() throws Exception {
		Metamodel model = em.getEntityManagerFactory().getMetamodel();

		assertTrue("No entity mapping found", model.getEntities().size() > 0);

		for (EntityType entityType : model.getEntities()) {
			String entityName = entityType.getName();
			em.createQuery("select o from " + entityName + " o").getResultList();
			logger.info("ok: " + entityName);

		}
	}
}
