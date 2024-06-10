package com.alkewallet.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "balances")
public class Balance {

    @Id
    private UUID balanceId;

    private UUID accountId;

    private String currencyCode;

    private double amount;
}
