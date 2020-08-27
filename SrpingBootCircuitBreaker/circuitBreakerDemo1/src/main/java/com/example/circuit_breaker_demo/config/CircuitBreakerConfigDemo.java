package com.example.circuit_breaker_demo.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.common.ratelimiter.configuration.RateLimiterConfigCustomizer;
import io.github.resilience4j.common.timelimiter.configuration.TimeLimiterConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfigDemo {

  @Bean
  public CircuitBreakerConfigCustomizer customCircuitBreakerConfig() {
    return CircuitBreakerConfigCustomizer.of(
        "postAuctionCircuitBreaker",
        builder ->
            builder
                .failureRateThreshold(50)
                .minimumNumberOfCalls(5)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build());

    //    CircuitBreakerConfig circuitBreakerConfig =
    //        CircuitBreakerConfig.custom()
    //            .failureRateThreshold(50)
    //            .minimumNumberOfCalls(5)
    //            .waitDurationInOpenState(Duration.ofMillis(1000))
    //            .slidingWindowSize(2)
    //            .build();
    //
    //
    // CircuitBreakerRegistry.of(circuitBreakerConfig).circuitBreaker("postAuctionCircuitBreaker");

  }

  @Bean
  public TimeLimiterConfigCustomizer customTimeLimitConfig() {
    return TimeLimiterConfigCustomizer.of(
        "postAuctionTimeLimiter",
        builder ->
            builder.cancelRunningFuture(true).timeoutDuration(Duration.ofSeconds(5)).build());
  }

  @Bean
  public RateLimiterConfigCustomizer customRateLimitConfig() {
    return RateLimiterConfigCustomizer.of(
        "postAuctionRateLimiter",
        builder ->
            builder
                .limitRefreshPeriod(Duration.ofMillis(1))
                .limitForPeriod(10)
                .timeoutDuration(Duration.ofMillis(2500))
                .build());

    //    RateLimiterConfig config =
    //            RateLimiterConfig.custom()
    //                    .limitRefreshPeriod(Duration.ofMillis(1))
    //                    .limitForPeriod(10)
    //                    .timeoutDuration(Duration.ofMillis(2500))
    //                    .build();
    //
    //    RateLimiterRegistry.of(config).rateLimiter("postAuctionRateLimiter");
  }
}
