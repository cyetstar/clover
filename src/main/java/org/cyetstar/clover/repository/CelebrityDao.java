package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Celebrity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CelebrityDao extends PagingAndSortingRepository<Celebrity, Long> {

	Celebrity findByDoubanId(String doubanId);

}
