package com.jesse1983.graphqlab.resolvers;

import com.jesse1983.graphqlab.domain.*;
import com.jesse1983.graphqlab.repository.*;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import reactor.core.publisher.Sinks.Many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Many<Product> productSink;

    @Autowired
    private Many<Customer> customerSink;


    private Product buildProduct(ProductInput productInput) {
        return Product.builder()
            .price(productInput.getPrice())
            .stock(productInput.getStock())
            .title(productInput.getTitle())
            .build();
    }

    public Product createProduct(ProductInput productInput) {
        Product newProduct = buildProduct(productInput);
        productSink.tryEmitNext(newProduct);
        return productRepository.save(newProduct);
    }

    public Product updateProduct(ProductInput productInput) {
        Optional<Product> targetProduct = productRepository.findById(productInput.getId());
        if(targetProduct.isPresent()) {
            Product updatedProduct = buildProduct(productInput);
            updatedProduct.setId(targetProduct.get().getId());
            productSink.tryEmitNext(updatedProduct);
            
            return productRepository.save(updatedProduct);
        }
        throw new GraphQLException("Product id " + productInput.getId() + " does not exist.");
    }

    public Boolean destroyProduct(Long id) {
        Optional<Product> targetProduct = productRepository.findById(id);
        if(targetProduct.isPresent()) {
            productRepository.delete(targetProduct.get());
            return true;
        }
        throw new GraphQLException("Product id " + id + " does not exist.");
    }


    private Customer buildCustomer(CustomerInput customerInput) {
        return Customer.builder()
                .firstName(customerInput.getFirstName())
                .lastName(customerInput.getLastName())
                .email(customerInput.getEmail())
                .type(customerInput.getType())
                .build();
    }

    public Customer createCustomer(CustomerInput customerInput) {
        Customer newCustomer = buildCustomer(customerInput);
        customerSink.tryEmitNext(newCustomer);
        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(CustomerInput customerInput) {
        Optional<Customer> targetCustomer = customerRepository.findById(customerInput.getId());
        if(targetCustomer.isPresent()) {
            Customer updatedCustomer = buildCustomer(customerInput);
            updatedCustomer.setId(targetCustomer.get().getId());
            customerSink.tryEmitNext(updatedCustomer);

            return customerRepository.save(updatedCustomer);
        }
        throw new GraphQLException("Customer id " + customerInput.getId() + " does not exist.");
    }

    public Boolean destroyCustomer(Long id) {
        Optional<Customer> targetCustomer = customerRepository.findById(id);
        if(targetCustomer.isPresent()) {
            customerRepository.delete(targetCustomer.get());
            return true;
        }
        throw new GraphQLException("Customer id " + id + " does not exist.");
    }
}
