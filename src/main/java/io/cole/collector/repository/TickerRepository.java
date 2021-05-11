package io.cole.collector.repository;

import io.cole.collector.domain.Ticker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends MongoRepository<Ticker, String> {


}
