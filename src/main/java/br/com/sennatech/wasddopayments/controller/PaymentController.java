package br.com.sennatech.wasddopayments.controller;

import br.com.sennatech.wasddopayments.domain.FinalPaymentResponse;
import br.com.sennatech.wasddopayments.domain.dto.PaymentRequestDTO;
import br.com.sennatech.wasddopayments.integration.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final KafkaProducer kafkaProducer;
    @PostMapping
    public ResponseEntity<FinalPaymentResponse> createpayment(@RequestBody PaymentRequestDTO request) {
        var completoPayment = kafkaProducer.send(request);
        return ResponseEntity.ok().body(completoPayment);
    }
}