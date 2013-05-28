package org.cyetstar.clover.utils;

import static org.cyetstar.clover.utils.DoubanJsonKey.*;
import static org.cyetstar.core.jackson.JsonNodeHelper.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.cyetstar.clover.entity.Book;
import org.cyetstar.clover.entity.BookTag;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.cyetstar.clover.entity.MovieCountry;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.clover.entity.MovieLanguage;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DoubanParser {

	private static Map<String, String> moviePropertyMap = Maps.newHashMap();

	private static Map<String, String> celebrityPropertyMap = Maps.newHashMap();

	private static Map<String, String> bookPropertyMap = Maps.newHashMap();

	static {
		moviePropertyMap.put(MOVIE_DBID_KEY, "doubanId");
		moviePropertyMap.put(MOVIE_TITLE_KEY, "title");
		moviePropertyMap.put(MOVIE_ORIG_TITLE_KEY, "originalTitle");
		moviePropertyMap.put(MOVIE_SUBTYPE_KEY, "subtype");
		moviePropertyMap.put(MOVIE_YEAR_KEY, "year");
		moviePropertyMap.put(MOVIE_SUMMARY_KEY, "summary");
		moviePropertyMap.put(RATING_AVE_KEY, "rating");
		moviePropertyMap.put(MOVIE_NUM_RATER_KEY, "numRaters");

		celebrityPropertyMap.put(CELEBRITY_DBID_KEY, "doubanId");
		celebrityPropertyMap.put(CELEBRITY_NAME_KEY, "name");
		celebrityPropertyMap.put(CELEBRITY_NAME_EN_KEY, "nameEn");
		celebrityPropertyMap.put(CELEBRITY_GENDER_KEY, "gender");
		celebrityPropertyMap.put(CELEBRITY_SUMMARY_KEY, "summary");

		bookPropertyMap.put(BOOK_DBID_KEY, "doubanId");
		bookPropertyMap.put(BOOK_ISBN10_KEY, "isbn10");
		bookPropertyMap.put(BOOK_ISBN13_KEY, "isbn13");
		bookPropertyMap.put(BOOK_TITLE_KEY, "title");
		bookPropertyMap.put(BOOK_ORIG_TITLE_KEY, "originTitle");
		bookPropertyMap.put(BOOK_ALT_TITLE_KEY, "altTitle");
		bookPropertyMap.put(BOOK_SUBTITLE_KEY, "subtitle");
		bookPropertyMap.put(BOOK_PUBLISHER_KEY, "publisher");
		bookPropertyMap.put(BOOK_PUBDATE_KEY, "pubdate");
		bookPropertyMap.put(BOOK_AUTHOR_INTRO_KEY, "authorIntro");
		bookPropertyMap.put(BOOK_SUMMARY_KEY, "summary");
		bookPropertyMap.put(RATING_AVE_KEY, "rating");
		bookPropertyMap.put(RATING_NUM_KEY, "numRaters");

	}

	public static Movie toMovie(JsonNode root) {
		Movie movie = getEntity(root, Movie.class, moviePropertyMap);
		movie.setDuration(toDuration(root));
		movie.setAkas(toMovieAkas(root));
		movie.setCasts(toCasts(root));
		movie.setCountries(toMovieCountrys(root));
		movie.setDirectors(toDirectors(root));
		movie.setGenres(toMovieGenres(root));
		movie.setLanguages(toMovieLanguages(root));
		movie.setWriters(toWriters(root));
		return movie;
	}

	public static Celebrity toCelebrity(JsonNode root) {
		return getEntity(root, Celebrity.class, celebrityPropertyMap);
	}

	public static Book toBook(JsonNode root) {
		Book book = getEntity(root, Book.class, bookPropertyMap);
		book.setAuthor(toAuthor(root));
		book.setTranslator(toTranslator(root));
		book.setTags(toBookTags(root));
		return book;
	}

	private static String toDuration(JsonNode root) {
		return toCommaDelimitedString(root, MOVIE_DURATION_KEY);
	}

	private static Set<MovieAka> toMovieAkas(JsonNode root) {
		JsonNode node = getChildNode(root, MOVIE_AKA_KEY);
		List<MovieAka> list = getList(node, MovieAka.class, ImmutableMap.of(SELF, "title"));
		return list != null ? Sets.newLinkedHashSet(list) : null;
	}

	private static Set<MovieCountry> toMovieCountrys(JsonNode root) {
		JsonNode node = getChildNode(root, MOVIE_COUNTRY_KEY);
		List<MovieCountry> list = getList(node, MovieCountry.class, ImmutableMap.of(SELF, "value"));
		return list != null ? Sets.newLinkedHashSet(list) : null;
	}

	private static Set<MovieGenre> toMovieGenres(JsonNode root) {
		JsonNode node = getChildNode(root, MOVIE_GENRE_KEY);
		List<MovieGenre> list = getList(node, MovieGenre.class, ImmutableMap.of(SELF, "value"));
		return list != null ? Sets.newLinkedHashSet(list) : null;
	}

	private static Set<MovieLanguage> toMovieLanguages(JsonNode root) {
		JsonNode node = getChildNode(root, MOVIE_LANG_KEY);
		List<MovieLanguage> list = getList(node, MovieLanguage.class, ImmutableMap.of(SELF, "value"));
		return list != null ? Sets.newLinkedHashSet(list) : null;
	}

	private static Set<MovieCredit> toDirectors(JsonNode root) {
		return toMovieCredits(root, MOVIE_DIRECTOR_KEY, "director");
	}

	private static Set<MovieCredit> toCasts(JsonNode root) {
		return toMovieCredits(root, MOVIE_CAST_KEY, "cast");
	}

	private static Set<MovieCredit> toWriters(JsonNode root) {
		return toMovieCredits(root, MOVIE_WRITER_KEY, "writer");
	}

	private static Set<MovieCredit> toMovieCredits(JsonNode root, String nodeKey, String role) {
		Validate.notNull(root);
		Set<MovieCredit> set = Sets.newLinkedHashSet();
		JsonNode node = root.get(nodeKey);
		if (node != null && !node.isNull()) {
			if (node.isArray()) {
				for (int i = 0; i < node.size(); i++) {
					set.add(toMovieCredit(node.get(i), role));
				}
			} else {
				set.add(toMovieCredit(node, role));
			}
		}
		return set;
	}

	private static MovieCredit toMovieCredit(JsonNode root, String role) {
		MovieCredit credit = new MovieCredit();
		credit.setRole(role);
		credit.setCelebrity(toCelebrity(root));
		return credit;
	}

	private static String toAuthor(JsonNode root) {
		return toCommaDelimitedString(root, BOOK_AUTHOR_KEY);
	}

	private static String toTranslator(JsonNode root) {
		return toCommaDelimitedString(root, BOOK_TRANSLATOR_KEY);
	}

	private static Set<BookTag> toBookTags(JsonNode root) {
		JsonNode node = getChildNode(root, BOOK_TAG_KEY);
		List<BookTag> list = getList(node, BookTag.class, ImmutableMap.of("name", "value"));
		return list != null ? Sets.newLinkedHashSet(list) : null;
	}

	private static String toCommaDelimitedString(JsonNode root, String key) {
		JsonNode node = getChildNode(root, key);
		String[] array = getArrayString(node);
		return array != null ? null : StringUtils.join(array, ",");
	}

}
