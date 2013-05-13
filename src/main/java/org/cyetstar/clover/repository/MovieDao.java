package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieDao extends PagingAndSortingRepository<Movie, Long> {

	Movie findByTitle(String title);
}
