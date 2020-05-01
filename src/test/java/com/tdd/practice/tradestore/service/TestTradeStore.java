package com.tdd.practice.tradestore.service;


import com.tdd.practice.tradestore.model.Trade;
import com.tdd.practice.tradestore.provider.DateProvider;
import com.tdd.practice.tradestore.validator.ITradeValidator;
import com.tdd.practice.tradestore.validator.MaturityExpiryTradeValidator;
import com.tdd.practice.tradestore.validator.VersionTradeValidator;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestTradeStore {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static List<ITradeValidator> validatorList;
    private static DateProvider dateProvider;

    @BeforeClass
    public static void beforeClass(){
        validatorList = new ArrayList<>();
        validatorList.add(new VersionTradeValidator());
        validatorList.add(new MaturityExpiryTradeValidator());
        dateProvider = new DateProvider();
    }

    @Test
    public void testAddTradeToTradeStore(){
        TradeStore tradeStore = new TradeStore(dateProvider);
        tradeStore.setTradeValidators(validatorList);

        Trade trade = Trade.builder()
                .tradeId("T1")
                .version(1)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.parse("20/05/2020", formatter))
                .createdDate(LocalDate.now())
                .build();
        tradeStore.processTrade(trade);
        Trade storeTrade = tradeStore.getTradeById(trade.getTradeId());
        Assert.assertThat(storeTrade, Matchers.is(Matchers.equalTo(trade)));
    }


    @Test
    public void testOverrideTradeToTradeStore(){
        TradeStore tradeStore = new TradeStore(dateProvider);
        tradeStore.setTradeValidators(validatorList);

        Trade trade = Trade.builder()
                .tradeId("T1")
                .version(1)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.parse("20/05/2020", formatter))
                .createdDate(LocalDate.now())
                .build();
        tradeStore.processTrade(trade);
        Trade tradeTobeOverridden = Trade.builder()
                .tradeId("T1")
                .version(1)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.parse("25/05/2020", formatter))
                .createdDate(LocalDate.now())
                .build();
        tradeStore.processTrade(tradeTobeOverridden);
        Assert.assertThat(tradeStore.getTradeById(trade.getTradeId()), Matchers.is(Matchers.equalTo(tradeTobeOverridden)));
    }

    @Test(expected = ValidationException.class)
    public void testIgnoreTradeForLowerVersionReceived(){

        TradeStore tradeStore = new TradeStore(dateProvider);
        tradeStore.setTradeValidators(validatorList);

        Trade trade2 = Trade.builder()
                .tradeId("T1")
                .version(2)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.parse("20/05/2020", formatter))
                .createdDate(LocalDate.now())
                .build();
        tradeStore.processTrade(trade2);
        Trade trade1 = Trade.builder()
                .tradeId("T1")
                .version(1)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.parse("20/05/2020", formatter))
                .createdDate(LocalDate.now())
                .build();
        tradeStore.processTrade(trade1);
    }

    @Test(expected =  ValidationException.class)
    public void testTradeMaturityDateViolation(){
        TradeStore tradeStore = new TradeStore(dateProvider);
        tradeStore.setTradeValidators(validatorList);

        Trade trade = Trade.builder()
                .tradeId("T1")
                .version(1)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.now().minusDays(1))
                .createdDate(LocalDate.now())
                .build();

        tradeStore.processTrade(trade);
    }

    @Test
    public void testSetExpirationFlagOnMaturity(){
        DateProvider dateProvider = Mockito.mock(DateProvider.class);
        Mockito.when(dateProvider.getNow()).thenReturn(LocalDate.now().minusDays(1));
        TradeStore tradeStore = new TradeStore(dateProvider);
        tradeStore.setTradeValidators(validatorList);

        Trade trade = Trade.builder()
                .tradeId("T1")
                .version(1)
                .counterPartyId("CP-1")
                .bookId("B1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .build();

        tradeStore.processTrade(trade);
        Trade tradeInStore = tradeStore.getTradeById(trade.getTradeId());
        Assert.assertThat(tradeInStore.getExpired(), Matchers.is(Matchers.equalTo('Y')));
    }

}
