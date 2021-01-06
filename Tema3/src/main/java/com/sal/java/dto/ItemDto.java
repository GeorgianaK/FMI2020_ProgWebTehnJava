package com.sal.java.dto;

import lombok.Data;

@Data
public class ItemDto {
    private String name;
    private double pricePerItem;
    private int quantityOrdered;
}
