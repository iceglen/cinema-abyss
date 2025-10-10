package ru.artem.papyan.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {
    private Integer movieId;
    private String title;
    private String action;
    private Integer userId;
    private Double rating;
    private List<String> genres;
    private String description;
}