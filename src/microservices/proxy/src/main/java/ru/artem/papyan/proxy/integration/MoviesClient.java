package ru.artem.papyan.proxy.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.artem.papyan.proxy.model.HealthStatus;
import ru.artem.papyan.proxy.model.Movie;
import ru.artem.papyan.proxy.model.MovieInput;

import java.util.List;

@FeignClient(name = "movies-client", url = "${services.movies.url}")
public interface MoviesClient {

    @PostMapping("/api/movies")
    Movie createMovie(@RequestBody MovieInput movieInput);

    @GetMapping("/api/movies")
    List<Movie> getAllMovies();

    @GetMapping("/api/movies/health")
    HealthStatus getMoviesServiceHealth();
}
