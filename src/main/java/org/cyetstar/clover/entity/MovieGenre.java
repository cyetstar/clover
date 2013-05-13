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
@Table(name = "tb_movie_genre")
public class MovieGenre extends IdEntity {

	private String genre;

	private List<Movie> movies = Lists.newArrayList();

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_movie_movie_genre", joinColumns = { @JoinColumn(name = "genre_id") }, inverseJoinColumns = { @JoinColumn(name = "movie_id") })
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
