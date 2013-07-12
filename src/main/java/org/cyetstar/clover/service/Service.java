package org.cyetstar.clover.service;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Service<T, ID extends Serializable> {

	T save(T entity);

	void delete(ID id);

	T findOne(ID id);

	Iterable<T> findAll();

	Page<T> findAll(Pageable pageable);

	Page<T> findAll(Map<String, Object> queryMap, Pageable pageable);

}
