package br.com.sennatech.wasddopayments.integration;

import br.com.sennatech.wasddopayments.domain.FinalPaymentResponse;
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

    public FinalPaymentResponse send(PaymentRequestDTO request) {
        var paymentResponse = new FinalPaymentResponse();
        paymentResponse.setTransaction(generatesTransactionCode.createCode());
        paymentResponse.setAmount(request.getAmount());

        FinalPaymentResponse savedPayment = finalPaymentResponseRepository.save(paymentResponse);

        var finalPayment = new PaymentKafkaMessage();
        finalPayment.setData(savedPayment);

        this.kafkaTemplate.send(topicName,finalPayment );
        log.info("Published the amount [{}], to the kafka queue: [{}]",
                request.getAmount(),
                topicName
        );
        return savedPayment;
    }

}
