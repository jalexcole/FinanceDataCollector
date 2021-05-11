package io.cole.collector.service;

import io.cole.collector.domain.Ticker;

public interface TickerService {

    public void saveStock(Ticker ticker);

    public abstract void insert(Ticker ticker);

    public abstract void save(Ticker ticker);

    public abstract void delete(Ticker ticker);
}
