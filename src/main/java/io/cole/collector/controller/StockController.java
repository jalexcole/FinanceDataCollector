package io.cole.collector.controller;

import io.cole.collector.domain.FetchStock;
import io.cole.collector.repository.FetchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private FetchStockRepository stockRepository;

    public StockController(FetchStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

//    public FetchStock addStock(FetchStock fetchStock) {
//        return stockRepository.insert(fetchStock);
//    }

}
