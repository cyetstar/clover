package org.cyetstar.clover.entity;

import org.joda.time.DateTime;

public class ComicBook extends IdEntity<Long> {

	private String bookVol;

	private String scanner;

	private String image;

	private Comic comic;

	private DateTime createdAt;

	private DateTime updatedAt;

	public String getBookVol() {
		return bookVol;
	}

	public void setBookVol(String bookVol) {
		this.bookVol = bookVol;
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

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
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
