package io.cole.collector.service;

import io.cole.collector.repository.LiveStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiveStockServiceImpl implements stockService {

    @Autowired
    private LiveStockRepository liveStockRepository;
}
