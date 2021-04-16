package com.lab.library.web;

import com.lab.library.domain.Book;
import com.lab.library.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RequestMapping(value = {"/","/books"})
@RestController
public class BookController {
    private final BookServiceImpl bookServiceImpl;
    @Autowired
    public BookController(BookServiceImpl bookServiceImpl){
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping(value = {"/","/books"})

    public List<Book> index(){
        return bookServiceImpl.getAllBooks(0);
    }
    @GetMapping("/book")

    public Optional<Book> book(@RequestParam(required = false) String name){
        return bookServiceImpl.findBookByName(name);
    }
    @PostMapping("/decrement")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void decrement(@RequestParam(required = true) Long id){
        bookServiceImpl.decrementQuantity(id);
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public Optional<Book> edit(@PathVariable Long id){
        return bookServiceImpl.getBook(id);
    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void save(@RequestBody Book book){
        if(bookServiceImpl.getBook(book.getId()).isEmpty()) {
            bookServiceImpl.save(book);
        }

    }
    @PostMapping("/edit")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void edit(@RequestBody Book book){
        bookServiceImpl.save(book);
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@RequestParam Long id){
        bookServiceImpl.remove(id);
    }
}
