package com.carService.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auto_parts")
@Data
public class AutoPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "part_name")
    private String name;

    @Column(name = "part_description")
    private String description;

    @Column(name = "part_price")
    private double price;


}
