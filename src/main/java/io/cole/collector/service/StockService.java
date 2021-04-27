package io.cole.collector.service;

import io.cole.collector.domain.FetchStock;

import java.util.List;

public interface StockService {

    List<FetchStock> listall();

    FetchStock getById(String id);

    FetchStock saveOrUpdate(FetchStock fetchedStock);

    FetchStock insert(FetchStock fetchStock);

    FetchStock saveOrUpdateStocks(FetchStock fetchStock);
}
