package com.tdd.practice.tradestore.provider;

import java.time.LocalDate;

public class DateProvider{
   public LocalDate getNow(){
      return LocalDate.now();
   }
}