package com.alkewallet.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
public class Account {

    @Setter
    @Getter
    private String accountId;

    @Setter
    @Getter
    private String currencyCode;

    @Setter
    @Getter
    private double amount;


}
