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
@Table(name = "tb_movie_country")
public class MovieCountry extends IdEntity {

	private String value;

	private List<Movie> movies = Lists.newArrayList();

	public MovieCountry() {
	}

	public MovieCountry(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_relations_movie_country", joinColumns = { @JoinColumn(name = "country_id") }, inverseJoinColumns = { @JoinColumn(name = "movie_id") })
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
