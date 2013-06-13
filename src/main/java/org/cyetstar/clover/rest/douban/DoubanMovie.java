package org.cyetstar.clover.rest.douban;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoubanMovie {

	@JsonProperty("id")
	private String doubanId;

	private String title;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("aka")
	private List<String> akas;

	private String rating;

	@JsonProperty("ratings_count")
	private String numRaters;

	private String subtype;

	@JsonProperty("directors")
	private List<DoubanCelebrity> directorCelebrities;

	@JsonProperty("casts")
	private List<DoubanCelebrity> castCelebrities;

	@JsonProperty("writers")
	private List<DoubanCelebrity> writerCelebrities;

	private String year;

	private List<String> languages;

	private List<String> genres;

	private List<String> countries;

	private String summary;

	public String getDoubanId() {
		return doubanId;
	}

	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public List<String> getAkas() {
		return akas;
	}

	public void setAkas(List<String> akas) {
		this.akas = akas;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(String numRaters) {
		this.numRaters = numRaters;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public List<DoubanCelebrity> getDirectorCelebrities() {
		return directorCelebrities;
	}

	public void setDirectorCelebrities(List<DoubanCelebrity> directorCelebrities) {
		this.directorCelebrities = directorCelebrities;
	}

	public List<DoubanCelebrity> getCastCelebrities() {
		return castCelebrities;
	}

	public void setCastCelebrities(List<DoubanCelebrity> castCelebrities) {
		this.castCelebrities = castCelebrities;
	}

	public List<DoubanCelebrity> getWriterCelebrities() {
		return writerCelebrities;
	}

	public void setWriterCelebrities(List<DoubanCelebrity> writerCelebrities) {
		this.writerCelebrities = writerCelebrities;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * JSON child object property into Java object field
	 * 
	 * @param ratingMap
	 */
	@JsonProperty("rating")
	public void setRating(Map<String, Object> ratingMap) {
		rating = String.valueOf(ratingMap.get("average"));
	}

	public Set<DoubanMovieCredit> getDirectors() {
		Set<DoubanMovieCredit> set = Sets.newHashSet();
		if (this.directorCelebrities != null) {
			for (DoubanCelebrity celebrity : this.directorCelebrities) {
				DoubanMovieCredit credit = new DoubanMovieCredit();
				credit.setCelebrity(celebrity);
				set.add(credit);
			}
		}
		return set;
	}

	public Set<DoubanMovieCredit> getCasts() {
		Set<DoubanMovieCredit> set = Sets.newHashSet();
		if (this.castCelebrities != null) {
			for (DoubanCelebrity celebrity : this.castCelebrities) {
				DoubanMovieCredit credit = new DoubanMovieCredit();
				credit.setCelebrity(celebrity);
				set.add(credit);
			}
		}
		return set;
	}

	public Set<DoubanMovieCredit> getWriters() {
		Set<DoubanMovieCredit> set = Sets.newHashSet();
		if (this.writerCelebrities != null) {
			for (DoubanCelebrity celebrity : this.writerCelebrities) {
				DoubanMovieCredit credit = new DoubanMovieCredit();
				credit.setCelebrity(celebrity);
				set.add(credit);
			}
		}
		return set;
	}
}
