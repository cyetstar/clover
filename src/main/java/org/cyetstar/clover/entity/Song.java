package org.cyetstar.clover.entity;

import org.joda.time.DateTime;

public class Song extends IdEntity<Long> {

	private String xiamiId;

	private int trackId;

	private String songName;

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

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public Ablum getAblum() {
		return ablum;
	}

	public void setAblum(Ablum ablum) {
		this.ablum = ablum;
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
