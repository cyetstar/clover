package org.cyetstar.clover.rest.douban;

import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.web.client.RestTemplate;

public class DoubanRequestClient {

	private final static String APIKEY = "?apikey={apikey}";

	private final static String MOVIE_INFO_URL = "http://api.douban.com/v2/movie/subject/{id}";

	private final static String CELEBRITY_INFO_URL = "http://api.douban.com/v2/movie/celebrity/{id}";

	private final static String BOOK_INFO_URL = "http://api.douban.com/v2/book/{id}";

	private final static String MUSIC_INFO_URL = "http://api.douban.com/v2/music/{id}";

	private String apikey;

	private RestTemplate restTemplate = new RestTemplate();

	private static Mapper mapper = new DozerBeanMapper();

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Movie requestMovie(String doubanId) {
		DoubanMovie dbm = restTemplate.getForObject(MOVIE_INFO_URL + APIKEY, DoubanMovie.class, doubanId, apikey);
		return mapper.map(dbm, Movie.class);
	}

	public Celebrity requestCelebrity(String doubanId) {
		DoubanCelebrity dbc = restTemplate.getForObject(CELEBRITY_INFO_URL + APIKEY, DoubanCelebrity.class, doubanId,
				apikey);
		return mapper.map(dbc, Celebrity.class);
	}

}
