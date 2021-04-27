package io.cole.collector.service;

import io.cole.collector.domain.FetchStock;
import io.cole.collector.repository.FetchStockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService{
    private final FetchStockRepository repository;

    @Autowired
    public StockServiceImpl(FetchStockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FetchStock> listall() {
        List<FetchStock> stocks = new ArrayList<>();
        repository.findAll().forEach(stocks::add);
        return stocks;
    }

    @Override
    public FetchStock getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public FetchStock saveOrUpdate(FetchStock fetchedStock) {
        repository.save(fetchedStock);
        return fetchedStock;
    }

    @Override
    public FetchStock insert(FetchStock fetchStock) {
        // repository.insert(fetchStock);
        return fetchStock;
    }

    @Override
    public FetchStock saveOrUpdateStocks(FetchStock fetchStock) {
        return null;
    }
}
