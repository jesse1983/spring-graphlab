package com.jesse1983.graphqlab.repository;

import com.jesse1983.graphqlab.domain.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {
}

