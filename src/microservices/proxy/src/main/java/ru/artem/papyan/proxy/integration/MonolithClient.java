package ru.artem.papyan.proxy.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.artem.papyan.proxy.model.HealthStatus;
import ru.artem.papyan.proxy.model.Movie;
import ru.artem.papyan.proxy.model.MovieInput;
import ru.artem.papyan.proxy.model.User;

import java.util.List;

@FeignClient(name = "monolith-client", url = "${services.monolith.url}")
public interface MonolithClient {

    @PostMapping("/api/movies")
    Movie createMovie(@RequestBody MovieInput movieInput);

    @GetMapping("/api/movies")
    List<Movie> getAllMovies();

    @GetMapping("/api/movies/health")
    HealthStatus getMoviesServiceHealth();

    @GetMapping("/api/users")
    List<User> getUsers();

    @GetMapping("/api/users")
    User getUser(@RequestParam("id") Long id);
}