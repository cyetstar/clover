package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.MovieCountry;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieCountryDao extends PagingAndSortingRepository<MovieCountry, Long> {

	MovieCountry findByValue(String value);

}
