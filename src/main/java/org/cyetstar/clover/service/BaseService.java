package org.cyetstar.clover.service;

import java.io.Serializable;
import java.util.Map;

import org.cyetstar.clover.repository.JpaSpecRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class BaseService<T, ID extends Serializable> implements Service<T, ID> {

	protected abstract JpaSpecRepository<T, ID> getRepository();

	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public void delete(ID id) {
		getRepository().delete(id);
	}

	@Override
	public T findOne(ID id) {
		return getRepository().findOne(id);
	}

	@Override
	public Iterable<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Override
	public Page<T> findAll(Map<String, Object> queryMap, Pageable pageable) {
		return getRepository().findAll(null, pageable);
	}

}
