package com.sal.java.controller;

import com.sal.java.exception.NoOrderFoundException;
import com.sal.java.exception.NoProductFoundException;
import com.sal.java.exception.NoStockAvailableException;
import com.sal.java.model.OrderItem;
import com.sal.java.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody List<OrderItem> items) {
        try {
            return ResponseEntity.ok(orderService.save(items));
        } catch (NoProductFoundException | NoStockAvailableException e)  {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id) {
//        TODO: Tema 3: Implementati un endpoint responsabil cu anularea unei comanzi plasate:
//              - schimbati statusul comenzii
//              - actualizati stocul produselor corespunzator
//              - deadline:  6 ianuarie 2021


// Am rescris laboratorul pentru a lucra cu PostgresSQL, JPARepository si Hibernate in rezolvarea cerintei

        try {
            return ResponseEntity.ok(orderService.cancelOrder(id));
        } catch (NoProductFoundException | NoOrderFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
