package org.cyetstar.clover.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

@Entity
@Table(name = "tb_parent")
public class Parent extends IdEntity {

	String name;

	List<Child> children = Lists.newArrayList();

	public Parent() {
	}

	public Parent(Long id) {
		this.id = id;
	}

	public Parent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public void addChild(Child c) {
		c.setParent(this);
		this.children.add(c);
	}

}
