package ru.artem.papyan.proxy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Movie {
    private Long id;
    private String title;
    private String description;
    private List<String> genres;
    private Double rating;
}