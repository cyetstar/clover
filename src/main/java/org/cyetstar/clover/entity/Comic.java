package org.cyetstar.clover.entity;

import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;

public class Comic extends IdEntity<Long> {

	private String title;

	private String originTitle;

	private String altTitle;

	private String author;

	private String publisher;

	private String authorIntro;

	private String summary;

	private boolean end;

	private int bookTotal;

	private String image;

	private List<ComicBook> books = Lists.newArrayList();

	private DateTime createdAt;

	private DateTime updatedAt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginTitle() {
		return originTitle;
	}

	public void setOriginTitle(String originTitle) {
		this.originTitle = originTitle;
	}

	public String getAltTitle() {
		return altTitle;
	}

	public void setAltTitle(String altTitle) {
		this.altTitle = altTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthorIntro() {
		return authorIntro;
	}

	public void setAuthorIntro(String authorIntro) {
		this.authorIntro = authorIntro;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getBookTotal() {
		return bookTotal;
	}

	public void setBookTotal(int bookTotal) {
		this.bookTotal = bookTotal;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ComicBook> getBooks() {
		return books;
	}

	public void setBooks(List<ComicBook> books) {
		this.books = books;
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
