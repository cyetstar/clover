package org.cyetstar.clover.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.shiro.util.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@Table(name = "tb_movie")
public class Movie extends IdEntity {

	public final static String IMDB_PREFIX = "tt";

	private String doubanId;

	private String imdb;

	private String title;

	private String originalTitle;

	private Set<MovieAka> akas = Sets.newLinkedHashSet();

	private String subtype;

	private String year;

	private String summary;

	private Set<MovieCredit> directors = Sets.newLinkedHashSet();

	private Set<MovieCredit> casts = Sets.newLinkedHashSet();

	private Set<MovieCredit> writers = Sets.newLinkedHashSet();

	private Set<MovieGenre> genres = Sets.newLinkedHashSet();

	private Set<MovieLanguage> languages = Sets.newLinkedHashSet();

	private Set<MovieCountry> countries = Sets.newLinkedHashSet();

	private String duration;

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
	public Set<MovieAka> getAkas() {
		return akas;
	}

	public void setAkas(Set<MovieAka> akas) {
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

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE })
	@Where(clause = "role='director'")
	public Set<MovieCredit> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<MovieCredit> directors) {
		this.directors = directors;
	}

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE })
	@Where(clause = "role='cast'")
	public Set<MovieCredit> getCasts() {
		return casts;
	}

	public void setCasts(Set<MovieCredit> casts) {
		this.casts = casts;
	}

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE })
	@Where(clause = "role='writer'")
	public Set<MovieCredit> getWriters() {
		return writers;
	}

	public void setWriters(Set<MovieCredit> writers) {
		this.writers = writers;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_movie_genre", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "genre_id") })
	public Set<MovieGenre> getGenres() {
		return genres;
	}

	public void setGenres(Set<MovieGenre> genres) {
		this.genres = genres;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_movie_language", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "language_id") })
	public Set<MovieLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<MovieLanguage> languages) {
		this.languages = languages;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_movie_country", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "country_id") })
	public Set<MovieCountry> getCountries() {
		return countries;
	}

	public void setCountries(Set<MovieCountry> countries) {
		this.countries = countries;
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
		if (aka != null) {
			aka.setMovie(this);
			this.akas.add(aka);
		}
	}

	public void addAllAka(Collection<MovieAka> akas) {
		for (MovieAka aka : akas) {
			this.addAka(aka);
		}
	}

	@Transient
	public List<String> getDurations() {
		return this.duration != null ? Lists.newArrayList(StringUtils.split(this.duration)) : null;
	}
}
