package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Musician;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MusicianDao extends PagingAndSortingRepository<Musician, Long> {

}
