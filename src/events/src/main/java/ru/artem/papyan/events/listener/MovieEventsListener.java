package ru.artem.papyan.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieEventsListener {

    public static final String TOPIC = "movie-events";

    @KafkaListener(topics = TOPIC, groupId = "ru-artem-papyan-events")
    public void listenMovieEvents(String message) {
        log.info("Received movie event: {}", message);
    }
}