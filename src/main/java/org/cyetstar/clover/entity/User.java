package org.cyetstar.clover.entity;

import java.util.Collection;

import org.cyetstar.code.shiro.ShiroUser;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public class User extends IdEntity<Long> implements ShiroUser {

	private String loginName;

	private String password;

	private String name;

	private DateTime createdAt;

	private DateTime updatedAt;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getSalt() {
		return null;
	}

	@Override
	public Collection<String> getRoles() {
		return Lists.newArrayList("user");
	}

	@Override
	public <T> T getActualObject() {
		return (T) this;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
