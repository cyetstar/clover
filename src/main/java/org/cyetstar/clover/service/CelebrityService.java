package org.cyetstar.clover.service;

import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.repository.CelebrityDao;
import org.cyetstar.clover.repository.JpaSpecRepository;
import org.cyetstar.core.domain.Clause;
import org.cyetstar.core.spring.SpecificationCreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CelebrityService extends BaseService<Celebrity, Long> {

	@Autowired
	CelebrityDao celebrityDao;

	@Override
	protected JpaSpecRepository<Celebrity, Long> getRepository() {
		return celebrityDao;
	}

	public Page<Celebrity> findCelebrity(String name, int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize);
		Specification<Celebrity> spec = SpecificationCreater.searchBy(Clause.instance().like("name", name)
				.like("nameEn", name).disjunction());
		return celebrityDao.findAll(spec, pageable);
	}

}
