package org.cyetstar.clover.repository;

import java.util.List;

import org.cyetstar.clover.entity.MovieSet;
import org.springframework.data.jpa.repository.Query;

public interface MovieSetDao extends JpaSpecRepository<MovieSet, Long> {

	@Query("from MovieSet a where not exists (from a.items b where b.movie.id = ?) order by a.createdAt desc")
	public List<MovieSet> findUnAddInSets(Long movieId);

	@Query("select a from MovieSet a join a.items b with b.movie.id = ? order by b.createdAt desc")
	public List<MovieSet> findAddInSets(Long movieId);

}
