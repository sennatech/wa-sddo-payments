package br.com.sennatech.wasddopayments.integration;

import br.com.sennatech.wasddopayments.domain.dto.PaymentKafkaMessage;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ProducerMessageSerializer extends JsonSerializer<PaymentKafkaMessage> {
}
