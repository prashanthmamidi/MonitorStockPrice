package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener {
    private ExecutionService executionService;
    private double thresholdPrice;
    private int volume;

    public PriceListenerImpl(ExecutionService executionService, double thresholdPrice, int volume) {
        this.executionService = executionService;
        this.thresholdPrice = thresholdPrice;
        this.volume = volume;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (price <= this.thresholdPrice) {
            executionService.buy(security, price, volume);
        }
    }
}
