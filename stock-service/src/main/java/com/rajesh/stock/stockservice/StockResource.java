package com.rajesh.stock.stockservice;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	private static final String HTTP_REST_DB_URL = "http://localhost:8762/db-service/rest/db/";
	@Autowired
    RestTemplate restTemplate;

    @GetMapping("/{username}")
    public List<StockQuote> getStockByUser(@PathVariable("username") final String userName) {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange(HTTP_REST_DB_URL + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });


        List<String> quotes = quoteResponse.getBody();
        return quotes
                .stream()
                .map(this::getStock)
                .collect(Collectors.toList());
    }
	
    private StockQuote getStock(String stock){		
		try {
			return YahooFinance.get(stock).getQuote();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
     }

}
