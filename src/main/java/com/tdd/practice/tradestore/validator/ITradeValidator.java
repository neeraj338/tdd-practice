package com.tdd.practice.tradestore.validator;


import com.tdd.practice.tradestore.model.Trade;
import com.tdd.practice.tradestore.service.TradeStore;

import javax.validation.ValidationException;


public interface ITradeValidator {
     void validate(TradeStore tradeStore, Trade newTrade) throws ValidationException;
}
