package com.ne.java.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "code", length = 20)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "price", length = 50)
    private double price;

    @Column(name = "in_date", length = 50)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inDate;
    @OneToMany(mappedBy = "product")
    private List<Quantity> quantities;
    @PrePersist
    public void prePersist() {
        if (inDate == null) {
            inDate = new Date();
        }
    }
}