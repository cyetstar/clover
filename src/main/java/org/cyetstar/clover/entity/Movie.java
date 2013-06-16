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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.shiro.util.StringUtils;
import org.cyetstar.clover.service.PosterService;
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

	private Float rating;

	private Integer numRaters;

	private String poster;

	private DateTime createdAt;

	private DateTime updatedAt;

	private List<MovieFile> files = Lists.newArrayList();

	private List<MovieSetItem> sets = Lists.newArrayList();

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
	@OrderBy
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

	@OneToMany(mappedBy = "movie", orphanRemoval = true, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE })
	@Where(clause = "role='director'")
	@OrderBy
	public Set<MovieCredit> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<MovieCredit> directors) {
		this.directors = directors;
	}

	@OneToMany(mappedBy = "movie", orphanRemoval = true, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE })
	@Where(clause = "role='cast'")
	@OrderBy
	public Set<MovieCredit> getCasts() {
		return casts;
	}

	public void setCasts(Set<MovieCredit> casts) {
		this.casts = casts;
	}

	@OneToMany(mappedBy = "movie", orphanRemoval = true, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE })
	@Where(clause = "role='writer'")
	@OrderBy
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
	@JoinTable(name = "tb_movie_relations_movie_genre", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "genre_id") })
	@OrderBy
	public Set<MovieGenre> getGenres() {
		return genres;
	}

	public void setGenres(Set<MovieGenre> genres) {
		this.genres = genres;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_relations_movie_language", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "language_id") })
	@OrderBy
	public Set<MovieLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<MovieLanguage> languages) {
		this.languages = languages;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_relations_movie_country", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "country_id") })
	@OrderBy
	public Set<MovieCountry> getCountries() {
		return countries;
	}

	public void setCountries(Set<MovieCountry> countries) {
		this.countries = countries;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(Integer numRaters) {
		this.numRaters = numRaters;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
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

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	public List<MovieFile> getFiles() {
		return files;
	}

	public void setFiles(List<MovieFile> files) {
		this.files = files;
	}

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	public List<MovieSetItem> getSets() {
		return sets;
	}

	public void setSets(List<MovieSetItem> sets) {
		this.sets = sets;
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

	@Transient
	public String getOriginPoster() {
		return PosterService.getOriginPoster(this.poster);
	}

	@Transient
	public String getSmallPoster() {
		return PosterService.getSmallPoster(this.poster);
	}
}
