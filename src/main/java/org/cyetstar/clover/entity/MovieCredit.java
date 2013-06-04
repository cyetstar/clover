package org.cyetstar.clover.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_movie_credit")
public class MovieCredit extends IdEntity {

	public final static String DIRECTOR = "director";

	public final static String WRITER = "writer";

	public final static String CAST = "cast";

	private Movie movie;

	private String role;

	private Celebrity celebrity;

	public static MovieCredit newDirector() {
		MovieCredit credit = new MovieCredit();
		credit.setRole(DIRECTOR);
		return credit;
	}

	public static MovieCredit newWriter() {
		MovieCredit credit = new MovieCredit();
		credit.setRole(WRITER);
		return credit;
	}

	public static MovieCredit newCast() {
		MovieCredit credit = new MovieCredit();
		credit.setRole(CAST);
		return credit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "celebrity_id")
	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celebrity == null) ? 0 : celebrity.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieCredit other = (MovieCredit) obj;
		if (celebrity == null) {
			if (other.celebrity != null)
				return false;
		} else if (!celebrity.equals(other.celebrity))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
