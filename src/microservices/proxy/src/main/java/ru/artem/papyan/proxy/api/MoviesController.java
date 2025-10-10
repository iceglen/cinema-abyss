package ru.artem.papyan.proxy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.artem.papyan.proxy.integration.MonolithClient;
import ru.artem.papyan.proxy.integration.MoviesClient;
import ru.artem.papyan.proxy.model.HealthStatus;
import ru.artem.papyan.proxy.model.Movie;
import ru.artem.papyan.proxy.model.MovieInput;
import ru.artem.papyan.proxy.routing.RoutingRule;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final RoutingRule<Void, List<Movie>> routingRuleGet;
    private final RoutingRule<MovieInput, Movie> routingRulePost;
    private final RoutingRule<Void, HealthStatus> routingRuleHealth;

    @Autowired
    public MoviesController(
            MoviesClient moviesClient,
            MonolithClient monolithClient,
            @Value("${services.routing-percent}") int routingPercentage,
            @Value("${services.migrated}") boolean isMigrated
    ) {
        routingRuleGet = new RoutingRule<>(
                routingPercentage,
                _ -> isMigrated ? moviesClient.getAllMovies() : monolithClient.getAllMovies(),
                _ -> monolithClient.getAllMovies()
        );

        routingRulePost = new RoutingRule<>(
                routingPercentage,
                i -> isMigrated ? moviesClient.createMovie(i) : monolithClient.createMovie(i),
                monolithClient::createMovie
        );

        routingRuleHealth = new RoutingRule<>(
                routingPercentage,
                _ -> isMigrated ? moviesClient.getMoviesServiceHealth() : monolithClient.getMoviesServiceHealth(),
                _ -> monolithClient.getMoviesServiceHealth()
        );
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return routingRuleGet.route(null);
    }

    @PostMapping
    public Movie createMovie(@RequestBody MovieInput movieInput) {
        return routingRulePost.route(movieInput);
    }

    @GetMapping("/health")
    public HealthStatus getMoviesServiceHealth() {
        return routingRuleHealth.route(null);
    }
}
