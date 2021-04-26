package io.cole.collector.scheduledtasks;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.cole.collector.domain.FetchStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60 * 1000)
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
    }
}