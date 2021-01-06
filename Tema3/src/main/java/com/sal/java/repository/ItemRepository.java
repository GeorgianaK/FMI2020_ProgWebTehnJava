package com.sal.java.repository;

import com.sal.java.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findAllByOrderO_Id(final Long id);
}
