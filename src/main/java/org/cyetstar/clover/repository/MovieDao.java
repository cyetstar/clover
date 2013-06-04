package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Movie;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MovieDao extends JpaSpecRepository<Movie, Long> {

	Movie findByDoubanId(String doubanId);

	Movie findByImdb(String imdb);

	@Modifying
	@Query("update Movie set rating = ?1, numRaters = ?2 , updatedAt = ?3 where id = ?4")
	void updateRating(float rating, int numRaters, DateTime updatedAt, Long id);

}
