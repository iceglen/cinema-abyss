package ru.artem.papyan.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse<T> {
    private String status;
    private Integer partition;
    private Integer offset;
    private Event<T> event;
}
