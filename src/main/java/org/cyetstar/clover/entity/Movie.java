package org.cyetstar.clover.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;

@Entity
@Table(name = "tb_movie")
public class Movie extends IdEntity {

	public final static String IMDB_PREFIX = "tt";

	private String doubanId;

	private String imdb;

	private String title;

	private String originalTitle;

	private List<MovieAka> akas = Lists.newArrayList();

	private String subtype;

	private String year;

	private String summary;

	private List<MovieCredit> directors = Lists.newArrayList();

	private List<MovieCredit> casts = Lists.newArrayList();

	private List<MovieCredit> writers = Lists.newArrayList();

	private String language;

	private String duration;

	private List<MovieGenre> genres = Lists.newArrayList();

	private String country;

	private float rating;

	private int numRaters;

	private String image;

	private DateTime createdAt;

	private DateTime updatedAt;

	public Movie() {

	}

	public Movie(Long id) {
		this.id = id;
	}

	public String getDoubanId() {
		return doubanId;
	}

	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
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

	@OneToMany(mappedBy = "movie", orphanRemoval = true, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE })
	public List<MovieAka> getAkas() {
		return akas;
	}

	public void setAkas(List<MovieAka> akas) {
		this.akas = akas;
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

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	@Where(clause = "role='director'")
	public List<MovieCredit> getDirectors() {
		return directors;
	}

	public void setDirectors(List<MovieCredit> directors) {
		this.directors = directors;
	}

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	@Where(clause = "role='cast'")
	public List<MovieCredit> getCasts() {
		return casts;
	}

	public void setCasts(List<MovieCredit> casts) {
		this.casts = casts;
	}

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	@Where(clause = "role='writer'")
	public List<MovieCredit> getWriters() {
		return writers;
	}

	public void setWriters(List<MovieCredit> writers) {
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_movie_movie_genre", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "genre_id") })
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(int numRaters) {
		this.numRaters = numRaters;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void addAka(MovieAka aka) {
		aka.setMovie(this);
		this.akas.add(aka);
	}

	public void addAllAka(List<MovieAka> akas) {
		for (MovieAka aka : akas) {
			addAka(aka);
		}
	}

}
