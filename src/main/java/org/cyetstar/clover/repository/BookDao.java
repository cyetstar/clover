package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

}
