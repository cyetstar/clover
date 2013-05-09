package org.cyetstar.clover.entity;

import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;

public class Musician extends IdEntity<Long> {

	private String name;

	private String nameEn;

	private String summary;

	private String avatar;

	private List<Ablum> ablums = Lists.newArrayList();

	private DateTime createdAt;

	private DateTime updatedAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Ablum> getAblums() {
		return ablums;
	}

	public void setAblums(List<Ablum> ablums) {
		this.ablums = ablums;
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
