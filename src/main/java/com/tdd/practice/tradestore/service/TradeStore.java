package com.tdd.practice.tradestore.service;


import com.tdd.practice.tradestore.model.Trade;
import com.tdd.practice.tradestore.provider.DateProvider;
import com.tdd.practice.tradestore.validator.ITradeValidator;

import java.util.*;

public class TradeStore {
    private Map<String, Trade> tradeStore;
    private PriorityQueue<Trade> priorityQueue;
    private DateProvider dateProvider;
    private List<ITradeValidator> tradeValidators;

    public TradeStore(DateProvider dateProvider){
        tradeStore = new HashMap<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparing(Trade::getMaturityDate).reversed());
        this.tradeValidators = new ArrayList<>();
        this.dateProvider = dateProvider;
    }

    public void setTradeValidators(List<ITradeValidator> validators){

        this.tradeValidators.addAll(validators);
    }

    public void processTrade(Trade trade) throws RuntimeException{
        for (ITradeValidator validator: this.tradeValidators
             ) {
            validator.validate(this, trade);
        }
        tradeStore.put(trade.getTradeId(), trade);
        priorityQueue.add(trade);

        processTradeMaturity();
    }

    public void processTradeMaturity() {
        while (!priorityQueue.isEmpty()
                && priorityQueue.peek().getMaturityDate().isAfter(dateProvider.getNow())){
            Trade expiredTrade =  priorityQueue.poll();
            expiredTrade.setExpired('Y');
        }
    }

    public Trade getTradeById(String tradeId) {
        return tradeStore.get(tradeId);
    }
}
