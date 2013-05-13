package org.cyetstar.clover.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;

@Entity
@Table(name = "tb_ablum")
public class Ablum extends IdEntity {

	private String doubanId;

	private String xiamiId;

	private String title;

	private List<Musician> singers = Lists.newArrayList();

	private String altTitle;

	private String summary;

	private String publisher;

	private String pubdate;

	private String version;

	private float rating;

	private int numRaters;

	private String image;

	private List<Song> songs = Lists.newArrayList();

	private DateTime createdAt;

	private DateTime updatedAt;

	public String getDoubanId() {
		return doubanId;
	}

	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}

	public String getXiamiId() {
		return xiamiId;
	}

	public void setXiamiId(String xiamiId) {
		this.xiamiId = xiamiId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToMany
	@JoinTable(name = "tb_ablum_musician", joinColumns = { @JoinColumn(name = "ablum_id") }, inverseJoinColumns = { @JoinColumn(name = "musician_id") })
	public List<Musician> getSingers() {
		return singers;
	}

	public void setSingers(List<Musician> singers) {
		this.singers = singers;
	}

	public String getAltTitle() {
		return altTitle;
	}

	public void setAltTitle(String altTitle) {
		this.altTitle = altTitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(int numRaters) {
		this.numRaters = numRaters;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@OneToMany(mappedBy = "ablum", cascade = CascadeType.REMOVE)
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
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
