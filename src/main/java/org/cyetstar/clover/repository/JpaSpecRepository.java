package org.cyetstar.clover.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSpecRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	/**
	 * Returns a single entity matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	T findOne(Specification<T> spec);

	/**
	 * Returns all entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	List<T> findAll(Specification<T> spec);

	/**
	 * Returns a {@link Page} of entities matching the given
	 * {@link Specification}.
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<T> findAll(Specification<T> spec, Pageable pageable);

	/**
	 * Returns all entities matching the given {@link Specification} and
	 * {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	List<T> findAll(Specification<T> spec, Sort sort);

	/**
	 * Returns the number of instances that the given {@link Specification} will
	 * return.
	 * 
	 * @param spec
	 *            the {@link Specification} to count instances for
	 * @return the number of instances
	 */
	long count(Specification<T> spec);
}
