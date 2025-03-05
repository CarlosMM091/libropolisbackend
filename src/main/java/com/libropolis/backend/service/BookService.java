package com.libropolis.backend.service;

import com.libropolis.backend.model.Book;
import com.libropolis.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Optional<Book> searchByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
