package com.sal.java.repository;

import com.sal.java.model.OrderO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderO, Long> {

    public Optional<OrderO> findOrderOById(final Long id);
}
