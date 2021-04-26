package io.cole.collector.repository;

import io.cole.collector.domain.FetchStock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FetchStockRepository extends CrudRepository<FetchStock, Long> {
}
