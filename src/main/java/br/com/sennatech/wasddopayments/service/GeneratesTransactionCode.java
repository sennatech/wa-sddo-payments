package br.com.sennatech.wasddopayments.service;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GeneratesTransactionCode {
    public String createCode() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").substring(0, 24);
    }
}