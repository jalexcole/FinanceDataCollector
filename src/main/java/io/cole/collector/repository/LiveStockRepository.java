package io.cole.collector.repository;

import io.cole.collector.domain.LiveStock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LiveStockRepository extends MongoRepository<LiveStock, String> {
}
