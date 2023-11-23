package br.com.sennatech.wasddopayments.integration;

import br.com.sennatech.wasddopayments.domain.FinalPaymentResponse;
import br.com.sennatech.wasddopayments.domain.dto.FinalPaymentResponseKafka;
import br.com.sennatech.wasddopayments.domain.dto.PaymentKafkaMessage;
import br.com.sennatech.wasddopayments.domain.dto.PaymentRequestDTO;
import br.com.sennatech.wasddopayments.service.GeneratesTransactionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<Object, PaymentKafkaMessage> kafkaTemplate;
    private final GeneratesTransactionCode generatesTransactionCode;
    private final FinalPaymentResponseRepository finalPaymentResponseRepository;

    @Value("${topic.name}")
    private String topicName;

    public FinalPaymentResponseKafka send(PaymentRequestDTO request) {
        var paymentResponse = new FinalPaymentResponse();
        paymentResponse.setTransactionId(generatesTransactionCode.createCode());
        paymentResponse.setAmount(request.getAmount());

        FinalPaymentResponse savedPayment = finalPaymentResponseRepository.save(paymentResponse);

        var finalPaymentResponseKafka = new FinalPaymentResponseKafka();
        finalPaymentResponseKafka.setId(savedPayment.getId());
        finalPaymentResponseKafka.setTransactionId(savedPayment.getTransactionId());
        finalPaymentResponseKafka.setAmount(savedPayment.getAmount());
        finalPaymentResponseKafka.setDateTime(savedPayment.getDateTime());
        finalPaymentResponseKafka.setDocumentNumber(request.getDocument());

        var finalPayment = new PaymentKafkaMessage();
        finalPayment.setData(finalPaymentResponseKafka);

        log.info("FinalPaymentResponseKafka: {}", finalPaymentResponseKafka);

        this.kafkaTemplate.send(topicName, finalPayment);
        log.info("Publicado o valor [{}], na fila do Kafka: [{}]",
                request.getAmount(),
                topicName
        );

        return finalPaymentResponseKafka;
    }

    public FinalPaymentResponse sendToController(PaymentRequestDTO request) {
        var paymentResponse = new FinalPaymentResponse();
        paymentResponse.setTransactionId(generatesTransactionCode.createCode());
        paymentResponse.setAmount(request.getAmount());

        FinalPaymentResponse savedPayment = finalPaymentResponseRepository.save(paymentResponse);

        log.info("FinalPaymentResponse: {}", savedPayment);

        return savedPayment;
    }
}