package com.jesse1983.graphqlab.resolvers;

import com.jesse1983.graphqlab.domain.Customer;
import com.jesse1983.graphqlab.domain.Product;
import com.jesse1983.graphqlab.domain.Sale;
import com.jesse1983.graphqlab.repository.CustomerRepository;
import com.jesse1983.graphqlab.repository.ProductRepository;
import com.jesse1983.graphqlab.repository.SaleRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public List<Customer> customers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public List<Product> products() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Sale> sales() {
        return (List<Sale>) saleRepository.findAll();
    }
}
