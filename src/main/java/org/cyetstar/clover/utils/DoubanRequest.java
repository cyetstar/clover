package org.cyetstar.clover.utils;

import java.io.IOException;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DoubanRequest {

	private final static String MOVIE_INFO_URL = "http://api.douban.com/v2/movie/subject/:id";

	private final static String CELEBRITY_INFO_URL = "http://api.douban.com/v2/movie/celebrity/:id";

	private final static String BOOK_INFO_URL = "http://api.douban.com/v2/book/:id";

	private final static String MUSIC_INFO_URL = "http://api.douban.com/v2/music/:id";

	private String apiKey;

	public DoubanRequest(String apiKey) {
		this.apiKey = apiKey;
	}

	public JsonNode requestBookInfo(String id) {
		String url = BOOK_INFO_URL.replace(":id", id) + "?apikey=" + apiKey;
		String content = requestGet(url);
		return readResponse(content);
	}

	public JsonNode requestMovieInfo(String id) {
		String url = MOVIE_INFO_URL.replace(":id", id) + "?apikey=" + apiKey;
		String content = requestGet(url);
		return readResponse(content);
	}

	public JsonNode requestCelebrityInfo(String id) {
		String url = CELEBRITY_INFO_URL.replace(":id", id) + "?apikey=" + apiKey;
		String content = requestGet(url);
		return readResponse(content);
	}

	public JsonNode requestMusicInfo(String id) {
		String url = MUSIC_INFO_URL.replace(":id", id) + "?apikey=" + apiKey;
		String content = requestGet(url);
		return readResponse(content);
	}

	private JsonNode readResponse(String content) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Validate.notNull(content);
			return mapper.readTree(content);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	public String requestGet(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		} finally {
			httpGet.releaseConnection();
		}
		return null;
	}

}
