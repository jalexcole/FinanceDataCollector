package io.cole.collector.repository;

import io.cole.collector.domain.FetchStock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FetchStockRepository extends CrudRepository<FetchStock, String> {
    List<FetchStock> findByNameContaining(String name);
}
