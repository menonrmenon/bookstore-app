package com.cisco.dnac.service;

import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.repository.BookStoreApplicationRepository;
import com.cisco.dnac.response.BookStoreApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookStoreApplicationService {

    @Autowired
    BookStoreApplicationRepository bookRepo;


    public ResponseEntity<BookDetails> createBookDetails(BookDetails book) {

        BookDetails bookReq = new BookDetails(book.getName(),book.getAuthor(),book.getIsbn(),book.getGenre());
        BookDetails updatedBook = bookRepo.save(bookReq);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    }

    public BookStoreApplicationResponse updateBookDetails(int id, BookDetails book) {
        BookDetails updatedBook = bookRepo.save(book);
        BookStoreApplicationResponse response = new BookStoreApplicationResponse();
        response.setData(Arrays.asList(updatedBook));
        response.setMessage("Success");
        return response;
    }

    public BookStoreApplicationResponse getBookDetailsById(int id) {
        Optional<BookDetails> book = bookRepo.findById(id);
        BookStoreApplicationResponse response = new BookStoreApplicationResponse();
        response.setData(Arrays.asList(book.get()));
        return response;
    }

    public BookStoreApplicationResponse getAllBookDetails() {
        List<BookDetails> booksList = new ArrayList<>();
        booksList = bookRepo.findAll();
        BookStoreApplicationResponse response = new BookStoreApplicationResponse();
        response.setData(booksList);
        response.setMessage("Success");
        return response;
    }

    public BookStoreApplicationResponse deleteBookDetailsById(int id) {
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
}
