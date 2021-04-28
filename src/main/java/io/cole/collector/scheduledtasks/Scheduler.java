package io.cole.collector.scheduledtasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cole.collector.domain.FetchStock;
import io.cole.collector.repository.FetchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    @Autowired
    private FetchStockRepository stockRepository;

    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void reportCurrentTime() {
        // Place Scheduled tasks Here
        collectStocks();
    }


    public void collectStocks() {
        // Retrieve stock list from resources file in the form of a json file.

        // Use a stream to access this json file and use each symbol

        // Process List
        FetchStock fetchedStock = new FetchStock("DOGE-USD");

        List<String> stocks = fetchStocks();
        for (String stockSymbol: stocks) {
            fetchedStock = new FetchStock(stockSymbol);

            stockRepository.insert(fetchedStock);
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
