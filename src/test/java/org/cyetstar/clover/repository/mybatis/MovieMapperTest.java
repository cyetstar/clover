package org.cyetstar.clover.repository.mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles("test")
@ContextConfiguration("classpath*:/applicationContext.xml")
public class MovieMapperTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	MovieMapper mapper;

	@Test
	public void find() {
		mapper.find(1L);
	}

}
