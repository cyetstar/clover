package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieDao extends PagingAndSortingRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

	Movie findByDoubanId(String doubanId);

	Movie findByImdb(String imdb);

}
