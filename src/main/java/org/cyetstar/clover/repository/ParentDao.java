package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Parent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParentDao extends PagingAndSortingRepository<Parent, Long> {

	Parent findByName(String name);

}
