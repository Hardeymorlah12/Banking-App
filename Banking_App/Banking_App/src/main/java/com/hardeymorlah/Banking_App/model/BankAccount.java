package com.hardeymorlah.Banking_App.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity(name = "accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    private String accountNumber;

    @Setter
    @Getter
    private double accountBalance;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;

//    public BankAccount(){}

    public BankAccount(String accountNumber, double accountBalance, AccountUser user) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.user = user;
    }

}
