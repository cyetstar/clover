package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Movie;

public interface MovieDao extends JpaSpecRepository<Movie, Long> {

	Movie findByDoubanId(String doubanId);

	Movie findByImdb(String imdb);

}
