package br.com.sennatech.wasddopayments.domain.dto;

import br.com.sennatech.wasddopayments.domain.Installments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {
    private BigDecimal value;
    private String cardToken;
    private String document;
    private List<Installments> installments;

}
