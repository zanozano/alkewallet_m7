package com.alkewallet.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private UUID id;

    private UUID userId;

    private String currencyCode;
}
