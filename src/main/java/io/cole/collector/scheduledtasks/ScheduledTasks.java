package io.cole.collector.scheduledtasks;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cole.collector.domain.FetchStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void reportCurrentTime() {
        // Place Scheduled tasks Here
        log.info("The time is now {}", dateFormat.format(new Date()));
        collectStocks();
    }


    public void collectStocks() {
        // Retrieve stock list from resources file in the form of a json file.

        // Use a stream to access this json file and use each symbol

        // Process List
        FetchStock fetchedStock = new FetchStock("DOGE-USD");
        log.info(fetchedStock.toJSON());

        List<String> stocks = fetchStocks();
        for (String stockSymbol: stocks) {
            fetchedStock = new FetchStock(stockSymbol);
            log.info(fetchedStock.toJSON());
        }
    }

    public List<String> fetchStocks() {
        String path = "src/main/resources/static/";
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream;

        List<String> stocks = new ArrayList<String>();
        try {
            inputStream = new FileInputStream(path + "dji.json");

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
}