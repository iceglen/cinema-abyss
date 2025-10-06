package ru.artem.papyan.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventsListener {

    public static final String TOPIC = "payment-events";

    @KafkaListener(topics = TOPIC, groupId = "ru-artem-papyan-events")
    public void listenPaymentEvents(String message) {
        log.info("Received payment event: {}", message);
    }
}