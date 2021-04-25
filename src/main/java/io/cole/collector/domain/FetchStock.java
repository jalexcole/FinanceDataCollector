package io.cole.collector.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = this.name)
public class FetchStock {

    @JsonIgnore
    private Stock stock;


    private Double price;
    private Long fetchTime;
    private String name;
    private String symbol;
    private String currency;
    private String stockExchange;


    public FetchStock(String symbol) {
        fetchBySymbol(symbol);
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



}


