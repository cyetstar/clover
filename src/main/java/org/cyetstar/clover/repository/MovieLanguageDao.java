package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.MovieLanguage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieLanguageDao extends PagingAndSortingRepository<MovieLanguage, Long> {

	MovieLanguage findByValue(String value);

}
