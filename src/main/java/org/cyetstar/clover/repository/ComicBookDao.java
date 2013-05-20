package org.cyetstar.clover.repository;

import org.cyetstar.clover.entity.ComicBook;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComicBookDao extends PagingAndSortingRepository<ComicBook, Long>, JpaSpecificationExecutor<ComicBook> {

}
