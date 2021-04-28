package io.cole.collector.repository;

import io.cole.collector.domain.HistoricStock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricStockRepository extends MongoRepository<HistoricStock, Long> {
}
