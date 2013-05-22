package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Celebrity;

public interface CelebrityDao extends JpaSpecRepository<Celebrity, Long> {

	Celebrity findByDoubanId(String doubanId);

}
