package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PriceListenerImplTest {

    @Mock
    ExecutionService executionService;
    PriceListener priceListener;

    @Before
    public void setUp() {
        priceListener = new PriceListenerImpl(executionService, 55, 100);
    }

    @Test
    public void executeBuy_GivenPriceIsLessThanLimit() {
        priceListener.priceUpdate("some-secure-string", 50);
        Mockito.verify(executionService).buy("some-secure-string", 50, 100);
    }

    @Test
    public void doNotExecuteBuy_GivenPriceIsMoreThanLimit() {
        priceListener.priceUpdate("some-secure-string", 60);
        Mockito.verifyZeroInteractions(executionService);
    }
}