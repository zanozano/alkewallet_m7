package com.alkewallet.wallet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionResponse {
    private boolean success;
    private String message;

    public TransactionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
