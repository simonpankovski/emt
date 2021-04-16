package com.lab.library.repository;

import com.lab.library.domain.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long> {
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookById(Long id);
    @Transactional
    @Modifying
    void removeBookById(Long id);
    @Transactional
    @Modifying
    @Query("update book b set b.availableCopies = b.availableCopies - 1 where b.id = ?1")
    void decrementBookQuantity(Long id);

}
