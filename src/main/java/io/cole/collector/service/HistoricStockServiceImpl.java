package io.cole.collector.service;

import io.cole.collector.repository.HistoricStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoricStockServiceImpl implements stockService{

    @Autowired
    private HistoricStockRepository historicStockRepository;
}
