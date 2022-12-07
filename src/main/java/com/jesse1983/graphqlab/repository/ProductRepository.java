package com.jesse1983.graphqlab.repository;

import com.jesse1983.graphqlab.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}

