package com.myecom.myapp.service;


import com.myecom.myapp.domain.entity.orders.OrderDetails;
import com.myecom.myapp.domain.entity.user.User;
import com.myecom.myapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDetails> getOrderList(User user) {
        return orderRepository.findByUser(user);
    }
}
