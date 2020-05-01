package com.tdd.practice.tradestore.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor(force =  true)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Trade {

    @EqualsAndHashCode.Include
    private String tradeId;

    @EqualsAndHashCode.Include
    private int version;

    private String counterPartyId;

    @EqualsAndHashCode.Include
    private String bookId;

    private LocalDate maturityDate;

    private LocalDate createdDate;

    @Builder.Default private char expired = 'N';
}
