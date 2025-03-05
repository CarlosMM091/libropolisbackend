package com.libropolis.backend.controller;

import com.libropolis.backend.model.Book;
import com.libropolis.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public List<Book> searchByTitle(@PathVariable String title) {
        return bookService.searchByTitle(title);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> searchByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookService.searchByIsbn(isbn);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
