package io.cole.collector.repository;

import io.cole.collector.domain.FetchStock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



//@Repository
public interface FetchStockRepository extends MongoRepository<FetchStock, String> {
}
