package com.cisco.dnac.controller;

import com.cisco.dnac.exception.BookAlreadyExistsException;
import com.cisco.dnac.exception.BookNotFoundException;
import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.repository.BookStoreApplicationRepository;
import com.cisco.dnac.response.BookStoreApplicationResponse;
import com.cisco.dnac.service.BookStoreApplicationService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BookStoreApplicationController {

    static final Counter counter = Counter.build()
            .name("total_requests")
            .help("Total Number of Requests")
            .register();

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    BookStoreApplicationService bookService;

    @Autowired
    BookStoreApplicationRepository bookRepo;

    static final HashSet<String>uniqGenres = new HashSet<>();

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/books")
    public ResponseEntity<BookDetails> createBookDetails(@RequestBody BookDetails book) throws BookAlreadyExistsException {
        BookDetails updatedBook = bookService.createBookDetails(book);
        meterRegistry.counter("Total no:of requests served: ").increment();
        uniqGenres.add(book.getGenre());
        Metrics.gauge("Number of Unique Genres: ", uniqGenres.size());
        Metrics.gauge("Total number of books in the store: ", bookRepo.findAll().size());
        return new ResponseEntity<>(updatedBook,HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/book/{id}")
    public ResponseEntity updateBookDetails(@PathVariable int id, @RequestBody BookDetails book) throws BookNotFoundException {
        book.setId(id);
        meterRegistry.counter("Total no:of requests served: ").increment();
        uniqGenres.add(book.getGenre());
        Metrics.gauge("Number of Unique Genres: ", uniqGenres.size());
        return bookService.updateBookDetails(id,book);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/book/{id}")
    public ResponseEntity getBookDetailsById(@PathVariable int id) throws BookNotFoundException{
        ResponseEntity<BookDetails> book = bookService.getBookDetailsById(id);
        if(book != null ){
            meterRegistry.counter("Total no:of requests served: ").increment();
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        meterRegistry.counter("Total no:of requests served: ").increment();
        return null;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/books")
    public ResponseEntity getAllBookDetails() {
        meterRegistry.counter("Total no:of requests served: ").increment();
       return bookService.getAllBookDetails();

    }

    @DeleteMapping(path = "/book/{id}")
    public ResponseEntity deleteBookDetailsById(@PathVariable int id) throws BookNotFoundException{
        meterRegistry.counter("Total no:of requests served: ").increment();
        Optional<BookDetails> bookRes = bookRepo.findById(id);
        uniqGenres.remove(bookRes.get().getGenre());
        Metrics.gauge("Number of Unique Genres: ", uniqGenres.size());
        return bookService.deleteBookDetailsById(id);
    }

    @DeleteMapping(path = "/books")
    public ResponseEntity deleteAllBookDetails() {
        meterRegistry.counter("Total no:of requests served: ").increment();
        uniqGenres.clear();
        Metrics.gauge("Number of Unique Genres: ", uniqGenres.size());
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

    @ExceptionHandler(value = BookAlreadyExistsException.class)
    public ResponseEntity<String> BookAlreadyExistsException(BookAlreadyExistsException bookAlreadyExistsException) {
        return new ResponseEntity<String>("Book already exists", HttpStatus.CONFLICT);
    }

    private int getTotal(){
        return uniqGenres.size();
    }
}
