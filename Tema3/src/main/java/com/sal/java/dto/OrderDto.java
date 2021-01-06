package com.sal.java.dto;

import com.sal.java.dto.ItemDto;
import com.sal.java.model.Status;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private double totalPrice;
    private List<ItemDto> products;
    private Status status;

}
