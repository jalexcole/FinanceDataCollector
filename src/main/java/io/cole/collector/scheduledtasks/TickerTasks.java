package io.cole.collector.scheduledtasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cole.collector.domain.Ticker;
import io.cole.collector.service.TickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TickerTasks implements CommandLineRunner {


    private final TickerService tickerService;


    private static final Logger logger = LoggerFactory.getLogger(TickerTasks.class);

    @Autowired
    public TickerTasks(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void recordTicker() {
        // Place Scheduled tasks Here
        Instant instant = Instant.now();
        collectLiveStocks();
        logger.info("Collected Stocks at: " + instant.toString());


    }

    public void startFromEpoch() {

    }
    public void collectStocks() {
        // Get Collection Time
        Instant instant = Instant.now();
        if (tickerService != null) {
            logger.info("Ticker is not null");
        } else {
            logger.info("Ticker service is null");
        }
        // Retrieve stock list from resources file in the form of a json file.

        // Use a stream to access this json file and use each symbol

        // Process List


        List<String> stocks = fetchStocks();
        for (String stockSymbol: stocks) {

            Ticker ticker = new Ticker(stockSymbol, instant);

            if (tickerService != null) {
                tickerService.insert(ticker);
            }


        }
    }

    public void collectLiveStocks() {
        List<String> stocks = fetchStocks();
        for (String stockSymbol: stocks) {

            Ticker ticker = new Ticker(stockSymbol);

            tickerService.insert(ticker);
        }
    }

    public List<String> fetchStocks() {
        var path = "src/main/resources/static/";
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream;

        List<String> stocks = new ArrayList<String>();
        try {
            inputStream = new FileInputStream(path +
                    "dji.json");

            try {
                stocks = objectMapper.readValue(inputStream, new TypeReference<List<String>>(){});

            } catch (JsonProcessingException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stocks;
    }


    @Override
    public void run(String... args) throws Exception {

        logger.info("Indexing Stock Values");
        initDatabase();
        logger.info("Database Initialized");
    }

    public void initDatabase() {
        long epoch = 0L;
        Long currentTime = System.currentTimeMillis();
        String startDate = "2008/01/01T00:00:00";
        String pattern = "YY/MM/dd'T'HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Instant instant = Instant.ofEpochMilli(epoch);


        // Write database at calender object
        Ticker ticker = new Ticker("AAPL");
        tickerService.insert(ticker);
    }
}
