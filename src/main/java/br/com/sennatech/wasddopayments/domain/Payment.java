package br.com.sennatech.wasddopayments.domain;

import lombok.Data;

@Data
public class Payment {
    private double amount;
    private String cardToken;
    private String document;
}