package org.cyetstar.clover.data;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.joda.time.DateTime;

public class MovieData {

	public static Movie newMovie(String t) {

		Movie movie = new Movie();
		movie.setTitle(t);
		movie.setSubtype("movie");
		movie.setYear("1980");
		movie.setCreatedAt(DateTime.now());
		return movie;
	}

	public static MovieAka newMovieAka(String t) {
		MovieAka aka = new MovieAka();
		aka.setTitle(t);
		return aka;
	}

}
