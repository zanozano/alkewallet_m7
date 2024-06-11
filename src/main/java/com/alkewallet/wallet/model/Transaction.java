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

    private UUID senderId;

    private UUID receiverId;

    private String currencyCode;

    private String type;

    public Date getDate() {
        return this.transactionDate;
    }
}
