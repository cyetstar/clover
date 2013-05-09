package org.cyetstar.clover.entity;

public class MovieAka extends IdEntity<Long> {

	private Movie movie;

	private String title;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
