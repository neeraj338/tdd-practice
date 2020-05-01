package com.tdd.practice.tradestore.validator;


import com.tdd.practice.tradestore.model.Trade;
import com.tdd.practice.tradestore.service.TradeStore;

import javax.validation.ValidationException;
import java.time.LocalDate;

public class MaturityExpiryTradeValidator implements ITradeValidator{

    @Override
    public void validate(TradeStore tradeStore, Trade newTrade) throws ValidationException {
        if(newTrade.getMaturityDate().isBefore(LocalDate.now())){
            throw  new ValidationException("Invalid Maturity date: Maturity can't be before today's date ");
        }
    }
}
