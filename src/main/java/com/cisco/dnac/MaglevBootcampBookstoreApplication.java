package com.cisco.dnac;

import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.repository.BookStoreApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MaglevBootcampBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaglevBootcampBookstoreApplication.class, args);
	}
}
