package com.sal.java.mapper;

import com.sal.java.dto.ItemDto;
import com.sal.java.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto toDto(OrderItem itemOrdered) {
        ItemDto itemDto = new ItemDto();
        itemDto.setName(itemOrdered.getProduct().getName());
        itemDto.setPricePerItem(itemOrdered.getProduct().getPrice());
        itemDto.setQuantityOrdered(itemOrdered.getQuantity());

        return itemDto;
    }
}
