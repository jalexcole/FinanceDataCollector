package io.cole.collector.repository;

import io.cole.collector.domain.FetchStock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FetchStockRepository extends MongoRepository<FetchStock, String> {
    List<FetchStock> findByNameContaining(String name);
}
