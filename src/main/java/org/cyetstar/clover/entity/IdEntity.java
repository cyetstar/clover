package org.cyetstar.clover.entity;

import java.io.Serializable;

public class IdEntity<T extends Serializable> {

	protected T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

}
