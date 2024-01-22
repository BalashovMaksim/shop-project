package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.dao.OrderRepository;
import com.maksimbalashov.spring.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
