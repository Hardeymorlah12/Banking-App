package com.hardeymorlah.Banking_App.model;

import jakarta.persistence.*;

@Entity(name = "reset")
public class ResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountUser accountUser;

}
