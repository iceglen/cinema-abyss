package ru.artem.papyan.proxy.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "events-client", url = "${services.events.url}")
public interface EventsClient {

    @GetMapping("/api/events/{id}")
    String getEvent(@PathVariable("id") String id);

    @PostMapping("/api/events")
    String createEvent(@RequestBody String event);

    @GetMapping("/api/events")
    String getAllEvents();
}