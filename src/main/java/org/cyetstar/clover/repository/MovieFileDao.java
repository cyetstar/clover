package org.cyetstar.clover.repository;

import java.util.List;

import org.cyetstar.clover.entity.MovieFile;

public interface MovieFileDao extends JpaSpecRepository<MovieFile, Long> {

	public List<MovieFile> findByMovieId(Long movieId);

}
