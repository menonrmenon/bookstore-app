package com.cisco.dnac.service;

import com.cisco.dnac.exception.BookAlreadyExistsException;
import com.cisco.dnac.exception.BookNotFoundException;
import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.repository.BookStoreApplicationRepository;
import com.cisco.dnac.response.BookStoreApplicationResponse;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Repository
public class BookStoreApplicationService {

    @Autowired
    BookStoreApplicationRepository bookRepo;


    public BookDetails createBookDetails(BookDetails book) throws DuplicateKeyException {

        BookDetails bookReq = new BookDetails(book.getName(), book.getAuthor(), book.getIsbn(), book.getGenre());

        return bookRepo.save(bookReq);
       // return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    }

    public ResponseEntity updateBookDetails(int id, BookDetails book) {
        if (!bookRepo.existsById(id)) {
            throw new BookNotFoundException();
        }
        BookDetails updatedBook = bookRepo.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);

    }

    public ResponseEntity getBookDetailsById(int id) {
        if (!bookRepo.findById(id).isPresent()) {
            throw new BookNotFoundException();
        } else {
            Optional<BookDetails> book = bookRepo.findById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }

    }

    public ResponseEntity getAllBookDetails() {
        List<BookDetails> booksList = new ArrayList<>();
        booksList = bookRepo.findAll();
        return new ResponseEntity<>(booksList, HttpStatus.OK);

    }

    public ResponseEntity deleteBookDetailsById(int id) {
        if (!bookRepo.findById(id).isPresent()) {
            throw new BookNotFoundException();
        } else {
            bookRepo.deleteById(id);
            return new ResponseEntity<>("Book deleted", HttpStatus.OK);
        }

    }

    public ResponseEntity deleteAllBookDetails() {
        bookRepo.deleteAll();
        return new ResponseEntity<>("All Books deleted", HttpStatus.OK);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<String> BookAlreadyExistsException(DuplicateKeyException bookAlreadyExistsException) {
        return new ResponseEntity<String>("Book already exists", HttpStatus.CONFLICT);
    }
}
