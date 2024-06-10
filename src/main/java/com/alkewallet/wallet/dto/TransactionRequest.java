package com.alkewallet.wallet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequest {

    private String currencyCode;
    private double amount;
}
