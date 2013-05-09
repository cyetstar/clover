package org.cyetstar.clover.entity;

public class MovieGenre extends IdEntity<Long> {

	private Movie movie;

	private String genre;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
