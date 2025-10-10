package ru.artem.papyan.proxy.routing;

import lombok.Getter;

import java.util.Random;
import java.util.function.Function;

@Getter
public class RoutingRule<T, R> {

    private final int percentage;
    private final Function<T, R> target;
    private final Function<T, R> fallback;
    private final Random random;

    /**
     * Constructs a RoutingService with the specified parameters.
     *
     * @param percentage the probability (0-100) of calling the target supplier.
     *                   Values outside 0-100 will be clamped to valid range.
     * @param target     the primary supplier to call when routing decision favors target
     * @param fallback   the backup supplier to call when routing decision favors fallback
     * @throws IllegalArgumentException if target or fallback is null
     */
    public RoutingRule(
            int percentage,
            Function<T, R> target,
            Function<T, R> fallback
    ) {
        if (target == null) {
            throw new IllegalArgumentException("Target supplier cannot be null");
        }
        if (fallback == null) {
            throw new IllegalArgumentException("Fallback supplier cannot be null");
        }

        // Clamp percentage to valid range [0, 100]
        this.percentage = Math.clamp(percentage, 0, 100);
        this.target = target;
        this.fallback = fallback;
        this.random = new Random();
    }

    /**
     * Routes the call to either target or fallback supplier based on the configured percentage.
     *
     * @return the result from either target or fallback supplier
     */
    public R route(T param) {
        int randomValue = random.nextInt(100); // 0 to 99 inclusive
        if (randomValue < percentage) {
            return target.apply(param);
        } else {
            return fallback.apply(param);
        }
    }

    /**
     * Creates a new RoutingService with the same suppliers but different percentage.
     *
     * @param newPercentage the new percentage for routing
     * @return a new RoutingService instance
     */
    public RoutingRule<T, R> withPercentage(int newPercentage) {
        return new RoutingRule<>(newPercentage, this.target, this.fallback);
    }
}
