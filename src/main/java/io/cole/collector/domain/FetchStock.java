package io.cole.collector.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import javax.persistence.*;

@Entity
@Table(name = "Stocks")
public class FetchStock {

    @JsonIgnore
    private Stock stock;

    private Long fetchTime;
    private String name;
    private String symbol;
    private String currency;
    private String stockExchange;
    private Double price;
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public FetchStock(String symbol) {
        fetchBySymbol(symbol);
    }

    public FetchStock() {

    }

    public void fetchAPPL() {
        fetchBySymbol("AAPL"); // Fetch for testing purposes
    }

    public void fetchBySymbol(String symbol) {
        try {
            stock = YahooFinance.get(symbol);
            fetchTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processSymbol() {
        if (stock.isValid()) {
            price = stock.getQuote().getPrice().doubleValue();
            symbol = stock.getSymbol();
            currency = stock.getCurrency();
            stockExchange = stock.getStockExchange();

        }
    }

    public String stockToJson(Stock stock) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }


    public void setFetchTime(Long fetchTime) {
        this.fetchTime = fetchTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "value")
    public Double getPrice() {
        return price;
    }

    @Column(name = "fetch_time", nullable = false)
    public Long getFetchTime() {
        return fetchTime;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return symbol;
    }

    @Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }
    @Column(name = "stock_exchange", nullable = false)
    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String exchange){
        stockExchange = exchange;
    }


    public void setId(Long id) {
        this.id = id;
    }


}


