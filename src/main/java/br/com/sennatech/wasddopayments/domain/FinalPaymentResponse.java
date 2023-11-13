package br.com.sennatech.wasddopayments.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FinalPaymentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;
    @Column(nullable = false, length = 50)
    private String transaction;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();
}
