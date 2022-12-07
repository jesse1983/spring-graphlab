package com.jesse1983.graphqlab.repository;

import com.jesse1983.graphqlab.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}

