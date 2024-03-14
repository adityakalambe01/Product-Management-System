package com.products.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost", columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double cost;

    @ManyToOne
//    @JsonIgnore
    private Categories category;
}
