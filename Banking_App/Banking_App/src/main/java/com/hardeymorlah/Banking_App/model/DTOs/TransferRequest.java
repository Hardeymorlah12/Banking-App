package com.hardeymorlah.Banking_App.model.DTOs;

import lombok.Data;

@Data
public class TransferRequest {

    private String accountFrom;
    private String accountTo;
    private double amount;

}
