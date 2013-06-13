package org.cyetstar.clover.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

@Entity
@Table(name = "tb_movie_language")
public class MovieLanguage extends IdEntity {

	private String value;

	private List<Movie> movies = Lists.newArrayList();

	public MovieLanguage() {
	}

	public MovieLanguage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_movie_language", joinColumns = { @JoinColumn(name = "language_id") }, inverseJoinColumns = { @JoinColumn(name = "movie_id") })
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
