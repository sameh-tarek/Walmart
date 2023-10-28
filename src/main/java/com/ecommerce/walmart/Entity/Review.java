package com.ecommerce.walmart.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float Rating;
    private String ratingText;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;
}
