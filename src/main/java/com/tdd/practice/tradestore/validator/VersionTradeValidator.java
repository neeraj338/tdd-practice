package com.tdd.practice.tradestore.validator;


import com.tdd.practice.tradestore.model.Trade;
import com.tdd.practice.tradestore.service.TradeStore;

import javax.validation.ValidationException;

public class VersionTradeValidator implements ITradeValidator{
    @Override
    public void validate(TradeStore tradeStore, Trade newTrade) throws ValidationException {
        Trade tradeInStore = tradeStore.getTradeById(newTrade.getTradeId());
        if(null!=tradeInStore &&
                newTrade.getVersion() < tradeInStore.getVersion()){
            throw  new ValidationException("Invalid Trade Version Exception");
        }
    }
}
