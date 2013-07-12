package org.cyetstar.clover.service;

import java.util.List;

import org.cyetstar.clover.entity.MovieSet;
import org.cyetstar.clover.entity.MovieSetItem;
import org.cyetstar.clover.repository.JpaSpecRepository;
import org.cyetstar.clover.repository.MovieSetDao;
import org.cyetstar.clover.repository.MovieSetItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieSetService extends BaseService<MovieSet, Long> {

	@Autowired
	MovieSetDao setDao;

	@Autowired
	MovieSetItemDao itemDao;

	@Override
	protected JpaSpecRepository<MovieSet, Long> getRepository() {
		return setDao;
	}

	public List<MovieSet> findUnAddInSets(Long movieId) {
		return setDao.findUnAddInSets(movieId);
	}

	public List<MovieSet> findAddInSets(Long movieId) {
		return setDao.findAddInSets(movieId);
	}

	public MovieSet saveMovieSet(MovieSet set) {
		return setDao.save(set);
	}

	public void saveMovieSetItem(MovieSetItem item) {
		itemDao.save(item);
	}

}
