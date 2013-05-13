package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.MovieGenre;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieGenreDao extends PagingAndSortingRepository<MovieGenre, Long> {

}
