package org.cyetstar.clover.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;

@Entity
@Table(name = "tb_movie_set")
public class MovieSet extends IdEntity {

	private String title;

	private String summary;

	private List<MovieSetItem> items = Lists.newArrayList();

	private DateTime createdAt;

	private DateTime updatedAt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@OneToMany(mappedBy = "set", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	public List<MovieSetItem> getItems() {
		return items;
	}

	public void setItems(List<MovieSetItem> items) {
		this.items = items;
	}

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
