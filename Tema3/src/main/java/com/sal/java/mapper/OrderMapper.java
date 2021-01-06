package com.sal.java.mapper;

import com.sal.java.dto.ItemDto;
import com.sal.java.dto.OrderDto;
import com.sal.java.model.OrderItem;
import com.sal.java.model.OrderO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final ItemMapper itemMapper;

    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public OrderDto toDto(OrderO order, List<OrderItem> orderItems) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalPrice(order.getTotalPrice());

        List<ItemDto> itemDtos = orderItems.stream()
                .map(i -> itemMapper.toDto(i))
                .collect(Collectors.toList());

        orderDto.setProducts(itemDtos);

        return orderDto;
    }
}
