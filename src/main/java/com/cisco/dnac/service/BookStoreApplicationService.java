package com.cisco.dnac.service;

import com.cisco.dnac.exception.BookAlreadyExistsException;
import com.cisco.dnac.exception.BookNotFoundException;
import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.repository.BookStoreApplicationRepository;
import com.cisco.dnac.response.BookStoreApplicationResponse;
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


    public ResponseEntity<BookDetails> createBookDetails(BookDetails book) throws BookAlreadyExistsException {

        BookDetails bookReq = new BookDetails(book.getName(),book.getAuthor(),book.getIsbn(),book.getGenre());

        BookDetails updatedBook = bookRepo.save(bookReq);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    }

    public ResponseEntity updateBookDetails(int id, BookDetails book) throws BookNotFoundException {
        if (!bookRepo.existsById(id)) {
            throw new BookNotFoundException();
        }
        BookDetails updatedBook = bookRepo.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);

    }

    public ResponseEntity getBookDetailsById(int id) throws BookNotFoundException {
        Optional<BookDetails> book = bookRepo.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);

    }

    public ResponseEntity getAllBookDetails() {
        List<BookDetails> booksList = new ArrayList<>();
        booksList = bookRepo.findAll();
        return new ResponseEntity<>(booksList, HttpStatus.OK);

    }

    public BookStoreApplicationResponse deleteBookDetailsById(int id) throws BookNotFoundException{
        bookRepo.deleteById(id);
        BookStoreApplicationResponse response = new BookStoreApplicationResponse();
        response.setData(new ArrayList<>());
        response.setMessage("Book deleted with Id: " + id);
        return response;
    }

    public BookStoreApplicationResponse deleteAllBookDetails() {
        bookRepo.deleteAll();
        BookStoreApplicationResponse response = new BookStoreApplicationResponse();
        response.setData(new ArrayList<>());
        response.setMessage("All Book deleted ");
        return response;
    }

    @ExceptionHandler(value = BookAlreadyExistsException.class)
    public ResponseEntity<String> BookAlreadyExistsException(BookAlreadyExistsException bookAlreadyExistsException) {
        return new ResponseEntity<String>("Book already exists", HttpStatus.CONFLICT);
    }
}
