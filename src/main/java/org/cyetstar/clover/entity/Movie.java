package org.cyetstar.clover.entity;

import java.util.List;

import org.joda.time.DateTime;

public class Movie extends IdEntity<Long> {

	private String doubanId;

	private String title;

	private String originalTitle;

	private List<MovieAka> akas;

	private String doubanUrl;

	private String subtype;

	private String year;

	private String summary;

	private List<Celebrity> directors;

	private List<Celebrity> casts;

	private List<Celebrity> writers;

	private String language;

	private String duration;

	private List<MovieGenre> genres;

	private String country;

	private Rating rating;

	private String image;

	private DateTime createdAt;

	private DateTime updatedAt;

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

	public List<MovieAka> getAkas() {
		return akas;
	}

	public void setAkas(List<MovieAka> akas) {
		this.akas = akas;
	}

	public String getDoubanUrl() {
		return doubanUrl;
	}

	public void setDoubanUrl(String doubanUrl) {
		this.doubanUrl = doubanUrl;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Celebrity> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Celebrity> directors) {
		this.directors = directors;
	}

	public List<Celebrity> getCasts() {
		return casts;
	}

	public void setCasts(List<Celebrity> casts) {
		this.casts = casts;
	}

	public List<Celebrity> getWriters() {
		return writers;
	}

	public void setWriters(List<Celebrity> writers) {
		this.writers = writers;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<MovieGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<MovieGenre> genres) {
		this.genres = genres;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
