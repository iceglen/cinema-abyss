package ru.artem.papyan.events.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.artem.papyan.events.dto.MovieEvent;
import ru.artem.papyan.events.dto.PaymentEvent;
import ru.artem.papyan.events.dto.UserEvent;
import ru.artem.papyan.events.listener.MovieEventsListener;
import ru.artem.papyan.events.listener.PaymentEventsListener;
import ru.artem.papyan.events.listener.UserEventsListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public void sendMovieEvent(MovieEvent movieEvent) {
        try {
            String eventJson = objectMapper.writeValueAsString(movieEvent);
            kafkaTemplate.send(MovieEventsListener.TOPIC, eventJson);
            log.info("Sent movie event to topic 'movie-events': {}", eventJson);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize movie event: {}", movieEvent, e);
            throw new RuntimeException("Failed to serialize movie event", e);
        }
    }

    public void sendUserEvent(UserEvent userEvent) {
        try {
            String eventJson = objectMapper.writeValueAsString(userEvent);
            kafkaTemplate.send(UserEventsListener.TOPIC, eventJson);
            log.info("Sent user event to topic 'user-events': {}", eventJson);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize user event: {}", userEvent, e);
            throw new RuntimeException("Failed to serialize user event", e);
        }
    }

    public void sendPaymentEvent(PaymentEvent paymentEvent) {
        try {
            String eventJson = objectMapper.writeValueAsString(paymentEvent);
            kafkaTemplate.send(PaymentEventsListener.TOPIC, eventJson);
            log.info("Sent payment event to topic 'payment-events': {}", eventJson);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize payment event: {}", paymentEvent, e);
            throw new RuntimeException("Failed to serialize payment event", e);
        }
    }
}
