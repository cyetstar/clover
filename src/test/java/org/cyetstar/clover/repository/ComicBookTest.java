package org.cyetstar.clover.repository;

import java.util.List;

import org.cyetstar.clover.entity.ComicBook;
import org.cyetstar.code.domain.Clause;
import org.cyetstar.code.domain.Fetch;
import org.cyetstar.code.spring.SpecificationCreater;
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

@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class ComicBookTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ComicBookDao comicBookDao;

	@Test
	public void find() {

		Clause clause1 = Clause.instance().eq("comic.title", "abc");
		List<Clause> clauses = Lists.newArrayList(clause1);
		Fetch f = new Fetch("comic");
		Specification<ComicBook> spec = SpecificationCreater.searchWith(f);
		comicBookDao.findAll(spec, new PageRequest(0, 10, new Sort(Direction.ASC, "createdAt")));
	}
}
