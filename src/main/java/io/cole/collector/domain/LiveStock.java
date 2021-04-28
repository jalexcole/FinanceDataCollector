package io.cole.collector.domain;

import org.springframework.data.annotation.Id;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

// import javax.persistence.Entity;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;





public class LiveStock {

    @Id
    private String id;

    private Long fetchTime;
    private String name;
    private String symbol;
    private String currency;
    private String stockExchange;
    private Double price;

    public LiveStock(String symbol) {
        fetchBySymbol(symbol);
    }

    public LiveStock(String symbol, Long requestTime) {
        fetchBySymbol(symbol);
        fetchTime = requestTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
            name = stock.getName();

        }
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Long getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Long fetchTime) {
        this.fetchTime = fetchTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


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

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("Stock");
        stringBuilder.append("[");
        stringBuilder.append("id=" + id + ",");
        stringBuilder.append("symbol=" + symbol + ",");
        stringBuilder.append("fetch_time=" + fetchTime + ",");
        stringBuilder.append("name=" + name + ",");
        stringBuilder.append("price=" + price + ",");
        stringBuilder.append("currency=" + currency + ",");
        stringBuilder.append("stock_exchange=" + stockExchange);
        stringBuilder.append("]");


        return stringBuilder.toString();
    }



}


