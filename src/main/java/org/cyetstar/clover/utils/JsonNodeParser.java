package org.cyetstar.clover.utils;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.cyetstar.clover.entity.MovieCountry;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.clover.entity.MovieLanguage;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class JsonNodeParser {

	private final static String MOVIE_DBID_KEY = "id";
	private final static String MOVIE_TITLE_KEY = "title";
	private final static String MOVIE_ORIG_TITLE_KEY = "original_title";
	private final static String MOVIE_SUBTYPE_KEY = "subtype";
	private final static String MOVIE_YEAR_KEY = "year";
	private final static String MOVIE_NUM_RATER_KEY = "ratings_count";
	private final static String MOVIE_DURATION_KEY = "genres";//"durations";
	private final static String MOVIE_SUMMARY_KEY = "summary";

	private final static String MOVIE_AKA_KEY = "aka";
	private final static String MOVIE_COUNTRY_KEY = "countries";
	private final static String MOVIE_GENRE_KEY = "genres";
	private final static String MOVIE_LANG_KEY = "languages";

	private final static String MOVIE_DIRECTOR_KEY = "directors";
	private final static String MOVIE_CAST_KEY = "casts";
	private final static String MOVIE_WRITER_KEY = "writers";

	private final static String CELEBRITY_DBID_KEY = "id";
	private final static String CELEBRITY_NAME_KEY = "name";

	private final static String RATING_KEY = "rating";
	private final static String RATING_VAL_KEY = "value";

	public static Movie toMovie(JsonNode root) {
		Movie movie = new Movie();
		movie.setDoubanId(getString(root, MOVIE_DBID_KEY));
		movie.setTitle(getString(root, MOVIE_TITLE_KEY));
		movie.setOriginalTitle(getString(root, MOVIE_ORIG_TITLE_KEY));
		movie.setSubtype(getString(root, MOVIE_SUBTYPE_KEY));
		movie.setYear(getString(root, MOVIE_YEAR_KEY));
		movie.setSummary(getString(root, MOVIE_SUMMARY_KEY));
		movie.setNumRaters(getInt(root, MOVIE_NUM_RATER_KEY));
		movie.setRating(toRating(root));
		movie.setDuration(toDuration(root));

		movie.setAkas(Sets.newLinkedHashSet(toMovieAkas(root)));
		movie.setCasts(Sets.newLinkedHashSet(toCasts(root)));
		movie.setCountries(Sets.newLinkedHashSet(toMovieCountrys(root)));
		movie.setDirectors(Sets.newLinkedHashSet(toDirectors(root)));
		movie.setGenres(Sets.newLinkedHashSet(toMovieGenres(root)));
		movie.setLanguages(Sets.newLinkedHashSet(toMovieLanguages(root)));
		movie.setWriters(Sets.newLinkedHashSet(toWriters(root)));
		return movie;
	}

	private static String toDuration(JsonNode root) {
		if (root.has(MOVIE_DURATION_KEY)) {
			JsonNode node = root.get(MOVIE_DURATION_KEY);
			if (node.isArray()) {
				String[] array = new String[node.size()];
				for (int i = 0; i < array.length; i++) {
					array[i] = node.get(i).asText();
				}
				return StringUtils.join(array, ",");
			} else {
				return node.asText();
			}
		}
		return null;

	}

	public static List<MovieAka> toMovieAkas(JsonNode root) {
		return toList(root, MOVIE_AKA_KEY, MovieAka.class);
	}

	public static List<MovieCountry> toMovieCountrys(JsonNode root) {
		return toList(root, MOVIE_COUNTRY_KEY, MovieCountry.class);
	}

	public static List<MovieGenre> toMovieGenres(JsonNode root) {
		return toList(root, MOVIE_GENRE_KEY, MovieGenre.class);
	}

	public static List<MovieLanguage> toMovieLanguages(JsonNode root) {
		return toList(root, MOVIE_LANG_KEY, MovieLanguage.class);
	}

	public static List<MovieCredit> toDirectors(JsonNode root) {
		return toMovieCredits(root, MOVIE_DIRECTOR_KEY, "director");
	}

	public static List<MovieCredit> toCasts(JsonNode root) {
		return toMovieCredits(root, MOVIE_CAST_KEY, "cast");
	}

	public static List<MovieCredit> toWriters(JsonNode root) {
		return toMovieCredits(root, MOVIE_WRITER_KEY, "writer");
	}

	public static Celebrity toCelebrity(JsonNode root) {
		Celebrity celebrity = new Celebrity();
		celebrity.setDoubanId(getString(root, CELEBRITY_DBID_KEY));
		celebrity.setName(getString(root, CELEBRITY_NAME_KEY));
		return celebrity;
	}

	private static <T> List<T> toList(JsonNode root, String nodeKey, Class<T> classType) {
		Validate.notNull(root);
		List<T> list = Lists.newArrayList();
		JsonNode node = root.get(nodeKey);
		if (node != null) {
			if (node.isArray()) {
				for (int i = 0; i < node.size(); i++) {
					list.add(toListItem(node.get(i), classType));
				}
			} else {
				list.add(toListItem(node, classType));
			}
		}
		return list;
	}

	private static <T> T toListItem(JsonNode itemNode, Class<T> classType) {
		T entity = null;
		try {
			entity = classType.newInstance();
			Field field = classType.getDeclaredField("value");
			field.setAccessible(true);
			field.set(entity, itemNode.asText());
		} catch (Exception e) {
			throw new ClassCastException("com.fasterxml.jackson.databind.JsonNode 无法转为:" + classType.getName());
		}
		return entity;
	}

	private static List<MovieCredit> toMovieCredits(JsonNode root, String nodeKey, String role) {
		Validate.notNull(root);
		List<MovieCredit> list = Lists.newArrayList();
		JsonNode node = root.get(nodeKey);
		if (node != null) {
			if (node.isArray()) {
				for (int i = 0; i < node.size(); i++) {
					list.add(toMovieCredit(node.get(i), role));
				}
			} else {
				list.add(toMovieCredit(node, role));
			}
		}
		return list;
	}

	private static MovieCredit toMovieCredit(JsonNode root, String role) {
		MovieCredit credit = new MovieCredit();
		credit.setRole(role);
		credit.setCelebrity(toCelebrity(root));
		return credit;
	}

	private static float toRating(JsonNode root) {
		if (root.has(RATING_KEY)) {
			JsonNode node = root.get(RATING_KEY);
			if (node.has(RATING_VAL_KEY)) {
				return (float) node.get(RATING_VAL_KEY).asDouble();
			}
		}
		return 0.0f;
	}

	private static String getString(JsonNode root, String key) {
		if (root.has(key)) {
			return root.get(key).asText();
		} else {
			return null;
		}
	}

	private static int getInt(JsonNode root, String key) {
		if (root.has(key)) {
			return root.get(key).asInt();
		} else {
			return 0;
		}
	}

}
