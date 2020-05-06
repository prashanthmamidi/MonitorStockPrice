package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
    private PriceSource priceSource;
    private PriceListener priceListener;

    public TradingStrategy(PriceSource priceSource, PriceListener priceListener) {
        this.priceSource = priceSource;
        this.priceListener = priceListener;
        this.priceSource.addPriceListener(priceListener);
    }

    public void processStock(String security, String stockName) {
        double stockPrice = priceSource.getStockPrice(stockName);
        if (priceSource.getStockPrice(stockName) > 0) {
            priceListener.priceUpdate(security, stockPrice);
        }
    }
}
