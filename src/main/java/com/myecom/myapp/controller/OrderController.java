package com.myecom.myapp.controller;

import com.myecom.myapp.domain.entity.orders.OrderDetails;
import com.myecom.myapp.domain.entity.user.User;
import com.myecom.myapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    public List<OrderDetails> fetchOrders(@AuthenticationPrincipal User user) {
        return orderService.getOrderList(user);
    }
}
