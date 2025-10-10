package ru.artem.papyan.events.dto;

import java.util.List;

public record Movie(
    Long id,
    String title,
    String description,
    List<String> genres,
    Double rating
) {
}
