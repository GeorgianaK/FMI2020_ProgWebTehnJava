package com.sal.java.service;

import com.sal.java.dto.OrderDto;
import com.sal.java.exception.NoOrderFoundException;
import com.sal.java.exception.NoProductFoundException;
import com.sal.java.exception.NoStockAvailableException;
import com.sal.java.mapper.OrderMapper;
import com.sal.java.model.OrderItem;
import com.sal.java.model.OrderO;
import com.sal.java.model.Product;
import com.sal.java.model.Status;
import com.sal.java.repository.ItemRepository;
import com.sal.java.repository.OrderRepository;
import com.sal.java.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;

    public OrderDto save(List<OrderItem> items) {
        List<OrderItem> itemsToBeOrdered = items.stream()
                .map(
                        itemOrdered -> {
                            OrderItem item = new OrderItem();
                            Optional<Product> p = productRepository.findProductByName(itemOrdered.getProduct().getName());
                            if (p.isPresent()) {
                                item.setProduct(p.get());
                                item.setQuantity(itemOrdered.getQuantity());
                                if (itemOrdered.getQuantity() > item.getProduct().getAvailableStock()) {
                                    throw new NoStockAvailableException();
                                }
                            } else {
                                throw new NoProductFoundException();
                            }
                            return item;
                        }
                ).collect(Collectors.toList());
        if (items.size() != itemsToBeOrdered.size()) {
            throw new NoProductFoundException();
        }

        OrderO orderO =  new OrderO();
        orderO.setStatus(Status.ACTIVE);

        Double totalPrice = itemsToBeOrdered.stream()
                .map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);
        orderO.setTotalPrice(totalPrice);
        orderRepository.save(orderO);

        itemsToBeOrdered.forEach(
                item -> {
                    productService.decrementStock(item.getProduct(), item.getQuantity());
                    itemRepository.save(item);
                }
        );
        return orderMapper.toDto(orderO, itemsToBeOrdered);
    }

    public OrderO cancelOrder(Long id) {
        Optional<OrderO> orderO = orderRepository.findOrderOById(id);
        if(orderO.isPresent()) {
            orderO.get().setStatus(Status.CANCELED);

            List<OrderItem> orderItems = itemRepository.findAllByOrderO_Id(id);
            for(OrderItem item: orderItems) {
                Optional<Product> findProduct = productRepository.findProductByName(item.getProduct().getName());
                if(findProduct.isPresent()) {
                    findProduct.get().setAvailableStock(findProduct.get().getAvailableStock() + item.getQuantity());
                }
                else {
                    throw new NoProductFoundException();
                }
                productRepository.save(findProduct.get());
            }
            orderRepository.save(orderO.get());
            return orderO.get();
        }
        else {
            throw new NoOrderFoundException();
        }
    }

}
