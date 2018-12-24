package com.rajesh.stock.dbservice.model;

import java.util.List;

import lombok.Data;

@Data
public class Quotes {

    private String userName;
    private List<String> quotes;

    public Quotes() {
    }

    public Quotes(String userName, List<String> quotes) {
        this.userName = userName;
        this.quotes = quotes;
    }
}
