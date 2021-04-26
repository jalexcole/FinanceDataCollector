package io.cole.collector;

import io.cole.collector.domain.FetchStock;
import io.cole.collector.repository.FetchStockRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableMongoRepositories(basePackageClasses = FetchStockRepository.class)
@SpringBootApplication
@EnableScheduling
public class Collector {
    public static void main(String[] args) {
        SpringApplication.run(Collector.class, args);
    }
}
