package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket(Long productId, String username);
}
