package com.alkewallet.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private UUID transactionId;

    private double amount;

    private Date transactionDate;

    private String senderId;

    private String receiverId;

    private String currencyCode;

    private String type;
}
