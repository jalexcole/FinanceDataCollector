package io.cole.collector.repository;

import io.cole.collector.domain.FetchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FetchStockRepository extends JpaRepository<FetchStock, Long> {
}
