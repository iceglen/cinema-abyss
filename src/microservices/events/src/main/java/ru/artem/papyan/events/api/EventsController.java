package ru.artem.papyan.events.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.artem.papyan.events.dto.*;
import ru.artem.papyan.events.service.KafkaService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {

    private final KafkaService kafkaService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getEventsServiceHealth() {
        log.info("received health check request");
        return ResponseEntity.ok().body(Map.of("status", true));
    }

    @PostMapping("/movie")
    public ResponseEntity<EventResponse<MovieEvent>> createMovieEvent(@RequestBody MovieEvent movieEvent) {
        log.info("received movie event request: {}", movieEvent);

        kafkaService.sendMovieEvent(movieEvent);

        var response = new EventResponse<>(
                "success",
                0,
                0,
                new Event<>(UUID.randomUUID().toString(), "movie", LocalDateTime.now(), movieEvent)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/user")
    public ResponseEntity<EventResponse<UserEvent>> createUserEvent(@RequestBody UserEvent userEvent) {
        log.info("received user event request: {}", userEvent);

        kafkaService.sendUserEvent(userEvent);

        var response = new EventResponse<>(
                "success",
                0,
                0,
                new Event<>(UUID.randomUUID().toString(), "movie", LocalDateTime.now(), userEvent)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/payment")
    public ResponseEntity<EventResponse<PaymentEvent>> createPaymentEvent(@RequestBody PaymentEvent paymentEvent) {
        log.info("received payment event request: {}", paymentEvent);

        kafkaService.sendPaymentEvent(paymentEvent);

        var response = new EventResponse<>(
                "success",
                0,
                0,
                new Event<>(UUID.randomUUID().toString(), "movie", LocalDateTime.now(), paymentEvent)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}