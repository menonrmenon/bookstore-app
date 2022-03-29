package com.cisco.dnac.response;

import com.cisco.dnac.model.BookDetails;

import java.util.List;

public class BookStoreApplicationResponse {
    private List<BookDetails> data;
    private String message;

    public List<BookDetails> getData() {
        return data;
    }

    public void setData(List<BookDetails> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
