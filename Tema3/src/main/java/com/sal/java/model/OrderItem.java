package com.sal.java.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private OrderO orderO;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Product product;

    private int quantity;

    @JsonIgnore
    public OrderO getOrderO() {
        return orderO;
    }

    public Long getOrderId() {
        return orderO.getId();
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public Long getProductId() {
        return product.getId();
    }
}
