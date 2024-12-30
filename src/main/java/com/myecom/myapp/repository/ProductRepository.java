package com.myecom.myapp.repository;

import com.myecom.myapp.domain.entity.product.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product,Long> {
}
