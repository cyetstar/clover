package org.cyetstar.clover.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "tb_song")
public class Song extends IdEntity {

	private String xiamiId;

	private int trackId;

	private String name;

	private Ablum ablum;

	private DateTime createdAt;

	private DateTime updatedAt;

	public String getXiamiId() {
		return xiamiId;
	}

	public void setXiamiId(String xiamiId) {
		this.xiamiId = xiamiId;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "ablum_id")
	public Ablum getAblum() {
		return ablum;
	}

	public void setAblum(Ablum ablum) {
		this.ablum = ablum;
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
