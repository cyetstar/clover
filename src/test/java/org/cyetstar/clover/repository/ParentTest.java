package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Child;
import org.cyetstar.clover.entity.Parent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class ParentTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ParentDao dao;

	@Autowired
	ChildDao cdao;

	@Test
	public void create() {
		//		Parent p = new Parent("p1");
		//		Child c1 = new Child("c1");
		//		Child c2 = new Child("c2");
		//		p.addChild(c1);
		//		p.addChild(c2);
		//		dao.save(p);

		Parent p1 = dao.findByName("英雄");
		Child c3 = new Child("c3");
		c3.setParent(new Parent(1L));
		p1.getChildren().add(c3);
		dao.save(p1);
		//		assertEquals(3, p1.getChildren().size());
		//		//
		//		Long id = p1.getId();
		//		Parent p2 = new Parent(id);
		//		p2.setName("p2");
		//		dao.save(p2);
		//		p2 = dao.findByName("p2");
		//		assertEquals(0, p2.getChildren().size());
	}
}
