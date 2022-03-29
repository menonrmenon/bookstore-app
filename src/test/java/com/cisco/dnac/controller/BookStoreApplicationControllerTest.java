package com.cisco.dnac.controller;

import com.cisco.dnac.model.BookDetails;
import com.cisco.dnac.response.BookStoreApplicationResponse;
import com.cisco.dnac.service.BookStoreApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(value = BookStoreApplicationController.class)
public class BookStoreApplicationControllerTest {

    @MockBean
    private BookStoreApplicationService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateBooks() throws Exception {
        Mockito.when(bookService.createBookDetails(ArgumentMatchers.any())).thenReturn(ArgumentMatchers.any());

        BookDetails book = new BookDetails("test","test","test","test");
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson);
        int status = mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus();
        assertEquals(200,status);
    }

    @Test
    public void testUpdateBooks() throws Exception {
        //Mockito.when(bookService.updateBookDetails(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(ArgumentMatchers.anyString());

        BookDetails book = new BookDetails("test","test","test","test01");
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/book/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson);
        int status = mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus();
        assertEquals(200,status);
    }

   /* @Test
    public void testGetBooks() throws Exception {
        //Mockito.when(bookService.updateBookDetails(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(ArgumentMatchers.anyString());

        BookDetails book = new BookDetails("test","test","test","test01");
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book);
        List<BookDetails> sRequest = new ArrayList<BookDetails>();
        sRequest.add(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/books")
                .contentType(MediaType.APPLICATION_JSON);
        Mockito.when(bookService.getAllBookDetails()).thenReturn(new ResponseEntity<>(sRequest,HttpStatus.OK));
        ResponseEntity<String> mockRestEntity =
                new ResponseEntity<>(String.valueOf(sRequest), HttpStatus.OK);
        int status = mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus();

        Assertions.assertThat(mockRestEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(mockRestEntity.getBody()).isEqualTo(String.valueOf(sRequest));
    }*/



}
