package com.example.circuit_breaker_demo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class TestImpl implements Test {

  @Override
  @CircuitBreaker(name = "postAuctionCircuitBreaker",fallbackMethod = "fallBackCircuitBreaker")
  @RateLimiter(name = "postAuctionRateLimiter")
  public int getData() {
    return data();
  }

  int data() {
//    try {
//      Thread.sleep(10000);
//    } catch (Exception e) {
//
//    }
    return 10/0;
  }

  public int fallBackCircuitBreaker(Throwable e) {
    System.out.println("Circuit Breaker Error");
    System.out.println(e);
    return 1;
  }

  public int fallBackRateLimiter(Throwable e) {
    System.out.println("Circuit Breaker Error");
    System.out.println(e);
    return 2;
  }

  public int fallBackTimeLimiter(Throwable e) {
    System.out.println(e);
    return 3;
  }
}
