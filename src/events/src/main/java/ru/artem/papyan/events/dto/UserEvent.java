package ru.artem.papyan.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private Integer userId;
    private String username;
    private String email;
    private String action;
    private LocalDateTime timestamp;
}