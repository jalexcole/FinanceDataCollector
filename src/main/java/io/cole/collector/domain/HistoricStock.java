package io.cole.collector.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class HistoricStock {

    private String Id;
    private String symbol;
    private String date;
    private String name;
    private Double price;
    public String logTime;
    private String exchange;
    private String currency;
    private BigDecimal marketCap;

    public HistoricStock(String symbol, long dateTime) {
        // Interact with historic stock and upload that data to the
        Stock stock = null;
        try {
            stock = getHistoricStock(symbol, dateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }

        processStock(stock);
        // Convert dateTime to a human readable format in UTC Time.
        humanReadableTime(dateTime);
    }

    private Stock getHistoricStock(String symbol, long dateTime) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, -1 * (int) (System.currentTimeMillis() - dateTime));
        Stock stock = YahooFinance.get(symbol, calendar);
        return stock;
    }

    private void processStock(Stock stock){
        this.name = stock.getName();
        this.exchange = stock.getStockExchange();
        this.symbol = stock.getSymbol();
        this.currency = stock.getCurrency();
        this.price = stock.getQuote().getPrice().doubleValue();
        this.marketCap = stock.getQuote().getAsk();
    }

    public void humanReadableTime(Long dateTime) {
        String pattern = "UTC:YY-MM-ddTHH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Instant instant = Instant.ofEpochMilli(dateTime);
        this.logTime = dateTimeFormatter.format(instant);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


}
