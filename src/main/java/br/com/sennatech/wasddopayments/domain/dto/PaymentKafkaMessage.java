package br.com.sennatech.wasddopayments.domain.dto;

import br.com.sennatech.wasddopayments.domain.FinalPaymentResponse;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentKafkaMessage {
    String  timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    String operation = "PAYMENT_CREATED" ;
    String domain = "SEGURO";
    String origin = "Asseguraê";
    @Setter
    FinalPaymentResponse data;
}
