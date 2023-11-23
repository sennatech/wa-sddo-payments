package br.com.sennatech.wasddopayments.integration;

import br.com.sennatech.wasddopayments.domain.FinalPaymentResponse;
import br.com.sennatech.wasddopayments.domain.dto.FinalPaymentResponseKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalPaymentResponseRepository extends JpaRepository<FinalPaymentResponse, Long> {
    }
