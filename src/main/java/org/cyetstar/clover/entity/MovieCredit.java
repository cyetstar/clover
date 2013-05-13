package org.cyetstar.clover.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_movie_credit")
public class MovieCredit extends IdEntity {

	private Movie movie;

	private String role;

	private Celebrity celebrity;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "celebrity_id")
	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

}
