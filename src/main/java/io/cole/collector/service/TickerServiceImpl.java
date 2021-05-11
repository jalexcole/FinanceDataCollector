package io.cole.collector.service;

import io.cole.collector.domain.Ticker;
import io.cole.collector.repository.TickerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TickerServiceImpl implements TickerService{

    private static final Logger logger = LoggerFactory.getLogger(TickerServiceImpl.class);


    private final TickerRepository tickerRepository;

    @Autowired
    TickerServiceImpl(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }
    @Override
    public void saveStock(Ticker ticker) {
        try {
            tickerRepository.save(ticker);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            logger.error(ticker.toString());
        }


    }

    @Override
    public void insert(Ticker ticker) {
        tickerRepository.insert(ticker);
    }

    @Override
    public void save(Ticker ticker) {
        tickerRepository.save(ticker);
    }

    @Override
    public void delete(Ticker ticker) {
        tickerRepository.delete(ticker);
    }


}
