package ru.artem.papyan.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event<T> {
    private String id;
    private String type;
    private LocalDateTime timestamp;
    private T payload;
}