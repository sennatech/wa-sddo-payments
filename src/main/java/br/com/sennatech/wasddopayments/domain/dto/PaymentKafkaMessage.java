package br.com.sennatech.wasddopayments.domain.dto;

import br.com.sennatech.wasddopayments.domain.FinalPaymentResponse;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentKafkaMessage {
    LocalDateTime timestamp = LocalDateTime.now();
    String operation = "PAGAMENTO" ;
    String domain = "SEGURO";
    String origin = "SEGURATECH";
    @Setter
    FinalPaymentResponse data;
}
