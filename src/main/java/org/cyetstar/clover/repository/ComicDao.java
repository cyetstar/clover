package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Comic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComicDao extends PagingAndSortingRepository<Comic, Long> {

}
