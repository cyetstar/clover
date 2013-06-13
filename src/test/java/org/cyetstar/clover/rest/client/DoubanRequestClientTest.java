package org.cyetstar.clover.rest.client;

import static org.junit.Assert.*;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.rest.douban.DoubanRequestClient;
import org.junit.Test;

public class DoubanRequestClientTest {

	private DoubanRequestClient client = new DoubanRequestClient();

	@Test
	public void movieRequest() {
		String doubanId = "10537949";
		Movie movie = client.requestMovie(doubanId);
		assertEquals("恶之教典", movie.getTitle());
		assertEquals(2, movie.getAkas().size());
		assertTrue(movie.getRating() > 0);
	}

}
