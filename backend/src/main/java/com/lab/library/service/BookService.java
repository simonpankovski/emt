package com.lab.library.service;

import com.lab.library.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public interface BookService {
    Optional<Book> findBookByName(String name);
    Optional<Book> getBook(Long id);
    List<Book> getAllBooks(Integer pageId);
    void decrementQuantity(Long id);
    void save(Book book);
    void remove(Long id);
}
