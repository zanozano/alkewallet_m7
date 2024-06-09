package com.alkewallet.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Setter
    @Getter
    private double amount;

    @Setter
    @Getter
    private Date transactionDate;

    @Setter
    @Getter
    private String senderId;

    @Setter
    @Getter
    private String receiverId;

    @Setter
    @Getter
    private String currencyCode;

    @Setter
    @Getter
    private String type;
}
