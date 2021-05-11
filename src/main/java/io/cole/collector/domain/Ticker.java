package io.cole.collector.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Document(collection = "stocks")
public class Ticker {

    @Id
    private String id;

    @Indexed
    private String symbol;
    private String name;
    @Indexed
    private Long fetchTime;
    private String date; // ISO Date format in UTC Time

    private BigDecimal value;
    private Long volume;
    private String dividend;
    @Field("market_cap")
    private BigDecimal marketCap;

    private String currency;
    private String exchange;
    @Field("statistics")
    private String stats;
    private String quote;

    public Ticker(String symbol, Instant fetchInstant) {
        processSymbolInstant(symbol, fetchInstant);
    }

    public Ticker(String symbol, Long epochTime) {
        Instant instant = Instant.ofEpochMilli(epochTime);
        processSymbolInstant(symbol, instant);
    }

    public Ticker(String symbol) {
        this.symbol = symbol;
        try {
            Stock stock = YahooFinance.get(symbol);

            this.name = stock.getName();
            this.fetchTime = Instant.now().toEpochMilli();
            this.date = humanReadableTime(Instant.now());
            this.value = stock.getQuote().getPrice();
            this.volume = stock.getQuote().getVolume();
            this.dividend = stock.getDividend().toString();

            this.marketCap = stock.getStats().getMarketCap();
            this.currency = stock.getCurrency();
            this.exchange = stock.getStockExchange();
            this.stats = stock.getStats().toString();
            this.quote = stock.getQuote().toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(this);
        }
    }

    public void processSymbolInstant(String symbol, Instant fetchInstant) {
        Calendar calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(fetchInstant, ZoneId.systemDefault()));

        this.symbol = symbol;
        try {
            Stock stock = YahooFinance.get(symbol, calendar);

            this.name = stock.getName();
            this.fetchTime = fetchInstant.getEpochSecond();
            this.date = humanReadableTime(fetchInstant);
            this.value = stock.getQuote().getPrice();
            this.volume = stock.getQuote().getVolume();
            this.dividend = stock.getDividend().toString();

            this.marketCap = stock.getStats().getMarketCap();
            this.currency = stock.getCurrency();
            this.exchange = stock.getStockExchange();
            this.stats = stock.getStats().toString();
            this.quote = stock.getQuote().toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(this);
        }
    }

    public String humanReadableTime(Instant instant) {
        String pattern = "YY-MM-dd'T'HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return instant.toString();

    }

    @Override
    public String toString() {
        StringBuilder sb;
        sb = new StringBuilder("ticker");
        sb.append("[");
        sb.append("id=").append(id).append(",");
        sb.append("symbol=").append(symbol).append(",");
        sb.append("name=").append(name).append(",");
        sb.append("fetch_time=").append(fetchTime).append(",");
        sb.append("date=").append(date).append(",");
        sb.append("value=").append(value).append(",");
        sb.append("volume=").append(volume).append(",");
        sb.append("dividend=").append(dividend).append(",");
        sb.append("market_cap=").append(marketCap).append(",");
        sb.append("currency=").append(currency).append(",");
        sb.append("exchange=").append(exchange).append(",");
        sb.append("statistics=").append(stats);
        sb.append("]");
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Long fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getDividend() {
        return dividend;
    }

    public void setDividend(String dividend) {
        this.dividend = dividend;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
