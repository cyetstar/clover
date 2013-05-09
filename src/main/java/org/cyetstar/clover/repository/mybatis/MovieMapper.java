package org.cyetstar.clover.repository.mybatis;

import org.cyetstar.clover.entity.Movie;

public interface MovieMapper extends SqlMapper {

	Movie find(Long id);

	void insert(Movie movie);

	void update(Movie movie);

	void delete(Movie movie);

}
