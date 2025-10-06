package ru.artem.papyan.events.dto;

import java.time.OffsetDateTime;

public record Payment(
        Long id,
        Long userId,
        Double amount,
        OffsetDateTime timestamp
) {
}
