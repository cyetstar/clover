package org.cyetstar.clover.repository;

import java.util.List;

import org.cyetstar.clover.entity.MovieSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface MovieSetDao extends JpaSpecRepository<MovieSet, Long> {

	@Query("select s from MovieSet as s left join s.items as i with i.movie.id <> ?")
	public Page<MovieSet> findUnAddInSets(Long movieId, Pageable pageable);

	@Query("select s from MovieSet as s join s.items as i with i.movie.id = ?")
	public List<MovieSet> findAddInSets(Long movieId);

}
