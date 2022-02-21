package com.bkbwongo.bookbank.repository;

import com.bkbwongo.bookbank.jpa.models.Book;
import com.bkbwongo.bookbank.jpa.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author bkaaron
 * @created 04/02/2022 - 12:55 PM
 * @project bookbank
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    List<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    List<Book> findByYearContainingIgnoreCase(String year, Pageable pageable);
    List<Book> findByPublisherContainingIgnoreCase(String publisher, Pageable pageable);
    List<Book> findByMd5ContainingIgnoreCase(String md5);
    List<Book> findByTopic(Topic topic, Pageable pageable);
    Optional<Book> findByMd5IgnoreCase(String md5);
}
