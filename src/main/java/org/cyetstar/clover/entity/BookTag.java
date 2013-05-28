package org.cyetstar.clover.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.collect.Sets;

@Entity
@Table(name = "tb_book_tag")
public class BookTag extends IdEntity {

	private String name;

	private Set<Book> books = Sets.newLinkedHashSet();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_book_book_tag", joinColumns = { @JoinColumn(name = "tag_id") }, inverseJoinColumns = { @JoinColumn(name = "book_id") })
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
