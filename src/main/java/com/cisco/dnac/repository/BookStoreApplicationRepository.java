package com.cisco.dnac.repository;

import com.cisco.dnac.model.BookDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookStoreApplicationRepository extends MongoRepository<BookDetails,Integer> {
}
