package com.hardeymorlah.Banking_App.model.DTOs;

import lombok.Data;

@Data
public class OperationRequest {
    private String accountNumber;
    private double amount;
}
