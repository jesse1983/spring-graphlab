package com.jesse1983.graphqlab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private Double amount;
    private Double discount;

    @ManyToOne
    private Product product;
}
