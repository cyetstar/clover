package org.cyetstar.clover.service;

import org.cyetstar.clover.entity.MovieSetItem;
import org.cyetstar.clover.repository.JpaSpecRepository;
import org.cyetstar.clover.repository.MovieSetItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieSetItemService extends BaseService<MovieSetItem, Long> {

	@Autowired
	MovieSetItemDao itemDao;

	@Override
	protected JpaSpecRepository<MovieSetItem, Long> getRepository() {
		return itemDao;
	}

}
