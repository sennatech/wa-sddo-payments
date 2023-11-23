package br.com.sennatech.wasddopayments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinalPaymentResponseKafka {
    private Long id;
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime dateTime = LocalDateTime.now();
    private String documentNumber;


}
