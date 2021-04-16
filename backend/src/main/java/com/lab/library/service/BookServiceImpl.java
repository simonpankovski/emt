package com.lab.library.service;

import com.lab.library.domain.Book;
import com.lab.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> findBookByName(String name) {
        return bookRepository.findBookByName(name);
    }

    @Override
    public List<Book> getAllBooks(Integer pageId) {
        Pageable firstPageWithFiveElements = PageRequest.of(pageId, 5);
        return bookRepository.findAll(firstPageWithFiveElements).getContent();
    }

    @Override
    public void decrementQuantity(Long id) {
        bookRepository.decrementBookQuantity(id);
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.removeBookById(id);
    }
}