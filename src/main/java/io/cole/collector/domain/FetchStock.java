package io.cole.collector.domain;


import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


@Entity
@Table(name = "stocks")
public class FetchStock {

    public static final Logger logger = LoggerFactory.getLogger(FetchStock.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long fetchTime;
    private String name;
    private String symbol;
    private String currency;
    private String stockExchange;
    private Double price;

    public FetchStock(String symbol) {
        fetchBySymbol(symbol);
    }

    public FetchStock() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void fetchBySymbol(String symbol) {
        Stock stock = null;
        try {
            stock = YahooFinance.get(symbol);
            fetchTime = System.currentTimeMillis();


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stock != null) processSymbol(stock);
    }

    private void processSymbol(Stock stock) {
        if (stock.isValid()) {
            price = stock.getQuote().getPrice().doubleValue();
            symbol = stock.getSymbol();
            currency = stock.getCurrency();
            stockExchange = stock.getStockExchange();

        }
    }

    @Column(name = "value", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "fetch_time", nullable = false)
    public Long getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Long fetchTime) {
        this.fetchTime = fetchTime;
    }

    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "stock_exchange", nullable = false)
    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String exchange) {
        stockExchange = exchange;
    }

    public String toJSON() {
        Instant instant = Instant.ofEpochMilli(fetchTime);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"id\":" + id + ",");
        stringBuilder.append("\"symbol\":" + "\"" + symbol + "\",");
        stringBuilder.append("\"fetch_time\":" + "\"" + date.toString() + "\",");
        stringBuilder.append("\"name\":" + "\"" + name + "\",");
        stringBuilder.append("\"price\":" + price + ",");
        stringBuilder.append("\"currency\":" + "\"" + currency + "\",");
        stringBuilder.append("\"stock_exchange\":" + "\"" + stockExchange + "\"");
        stringBuilder.append("}");


        return stringBuilder.toString();
    }


}


