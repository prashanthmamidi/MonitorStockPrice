package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {
    @Mock
    private PriceSource priceSource;
    @Mock
    private PriceListener priceListener;

    private TradingStrategy tradingStrategy;

    @Before
    public void setUp() {
        tradingStrategy = new TradingStrategy(priceSource, priceListener);
    }

    @Test
    public void processStock_verify_priceUpdateCalledWithTheRightStockPrice() {
        String stockName = "IBM", security = "some-security-key";
        double stockPrice = 60.0;

        when(priceSource.getStockPrice(stockName)).thenReturn(stockPrice);
        tradingStrategy.processStock(security, stockName);

        verify(priceListener).priceUpdate(security, stockPrice);
    }

    @Test
    public void processStock_verify_priceUpdateNotCalled_Given_StockPrice_Is_LessThanOrEqualToZero() {
        String stockName = "IBM", security = "some-security-key";
        double stockPrice = 0.0;

        when(priceSource.getStockPrice(stockName)).thenReturn(stockPrice);
        tradingStrategy.processStock(security, stockName);

        verifyZeroInteractions(priceListener);
    }
}
