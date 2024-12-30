package com.myecom.myapp.repository;

import com.myecom.myapp.domain.entity.orders.OrderDetails;
import com.myecom.myapp.domain.entity.user.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderDetails, Long> {
    List<OrderDetails> findByUser(User user);
}
