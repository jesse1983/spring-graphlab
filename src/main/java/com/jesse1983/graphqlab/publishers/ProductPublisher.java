package com.jesse1983.graphqlab.publishers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jesse1983.graphqlab.domain.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.util.concurrent.Queues;

@Configuration
public class ProductPublisher {

  @Bean
  public Many<Product> productSink() {
    return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
  }

  @Bean
  public Flux<Product> productFlux(Many<Product> productSink) {
    return productSink.asFlux();
  }

}