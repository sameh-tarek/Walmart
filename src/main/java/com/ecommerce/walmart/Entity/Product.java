package com.ecommerce.walmart.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    @ManyToOne
    private Category category;
    @ManyToMany
    private List<Image> images;
    @OneToMany
    private List<Review> reviews;
}
