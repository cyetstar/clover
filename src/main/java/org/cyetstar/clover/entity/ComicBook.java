package org.cyetstar.clover.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "tb_comic_book")
public class ComicBook extends IdEntity {

	private int bookVolume;

	private String scanner;

	private String image;

	private Comic comic;

	private DateTime createdAt;

	private DateTime updatedAt;

	public int getBookVolume() {
		return bookVolume;
	}

	public void setBookVolume(int bookVolume) {
		this.bookVolume = bookVolume;
	}

	public String getScanner() {
		return scanner;
	}

	public void setScanner(String scanner) {
		this.scanner = scanner;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToOne
	@JoinColumn(name = "comic_id")
	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
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
