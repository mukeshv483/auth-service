package com.myecom.myapp.controller;

import com.myecom.myapp.domain.entity.product.Product;
import com.myecom.myapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("product")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
