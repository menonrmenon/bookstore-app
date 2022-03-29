package com.cisco.dnac.controller;

import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.repository.BookStoreApplicationRepository;
import com.cisco.dnac.response.BookStoreApplicationResponse;
import com.cisco.dnac.service.BookStoreApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BookStoreApplicationController {

    @Autowired
    BookStoreApplicationService bookService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/books")
    public ResponseEntity<BookDetails> createBookDetails(@RequestBody BookDetails book) {
        return bookService.createBookDetails(book);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/book/{id}")
    public ResponseEntity updateBookDetails(@PathVariable int id, @RequestBody BookDetails book) {
        book.setId(id);
        return bookService.updateBookDetails(id,book);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/book/{id}")
    public ResponseEntity getBookDetailsById(@PathVariable int id) {
        ResponseEntity<BookDetails> book = bookService.getBookDetailsById(id);
        if(book != null ){
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return null;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/books")
    public ResponseEntity getAllBookDetails() {
       return bookService.getAllBookDetails();

    }

    @DeleteMapping(path = "/book/{id}")
    public BookStoreApplicationResponse deleteBookDetailsById(@PathVariable int id) {
       return bookService.deleteBookDetailsById(id);

    }

    @DeleteMapping(path = "/books")
    public BookStoreApplicationResponse deleteAllBookDetails() {
       return bookService.deleteAllBookDetails();

    }
    @GetMapping(path = "/ready")
    public String ready() {
        return "Ready!";
    }
    @GetMapping(path = "/live")
    public String live() {
        return "Live!";
    }
}
