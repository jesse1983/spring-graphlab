package com.jesse1983.graphqlab.resolvers;

import com.jesse1983.graphqlab.domain.*;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import reactor.core.publisher.Flux;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subscription implements GraphQLSubscriptionResolver {


    @Autowired
    protected Flux<Product> productEvents;

    @Autowired
    protected Flux<Customer> customerEvents;

    public Publisher<Product> productModified(Long id) {
        return productEvents
            .filter(product -> product.getId().equals(id))
            .map(product -> product);    
    }

    public Publisher<Customer> customerModified(Long id) {
        return customerEvents
                .filter(customer -> customer.getId().equals(id))
                .map(customer -> customer);
    }
}
