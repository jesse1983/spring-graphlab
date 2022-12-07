package com.jesse1983.graphqlab.publishers;

import com.jesse1983.graphqlab.domain.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.util.concurrent.Queues;

@Configuration
public class CustomerPublisher {

  @Bean
  public Many<Customer> customerSink() {
    return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
  }

  @Bean
  public Flux<Customer> customerFlux(Many<Customer> customerSink) {
    return customerSink.asFlux();
  }

}