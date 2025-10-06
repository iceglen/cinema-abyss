package ru.artem.papyan.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private Integer paymentId;
    private Integer userId;
    private Double amount;
    private String status;
    private LocalDateTime timestamp;
    private String methodType;
}