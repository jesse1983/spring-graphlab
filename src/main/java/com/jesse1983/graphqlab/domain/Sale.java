package com.jesse1983.graphqlab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    private String identifier;
    private Date date;
    private Double discount;
    private String comments;

    @OneToMany
    private List<Item> itens;

    @ManyToOne
    private Customer customer;
}
