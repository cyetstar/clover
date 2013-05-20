package org.cyetstar.clover.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_movie_aka")
public class MovieAka extends IdEntity {

	private Movie movie;

	private String value;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
