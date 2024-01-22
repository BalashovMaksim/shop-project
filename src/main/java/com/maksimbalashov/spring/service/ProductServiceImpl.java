package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.dao.ProductRepository;
import com.maksimbalashov.spring.domain.Bucket;
import com.maksimbalashov.spring.domain.Product;
import com.maksimbalashov.spring.domain.User;
import com.maksimbalashov.spring.dto.ProductDTO;
import com.maksimbalashov.spring.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if(user==null){
            throw new RuntimeException("User not found - " + username);
        }

        Bucket bucket = user.getBucket();

        if(bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        }else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }

    public ProductDTO getById(Long id){
        Product product = productRepository.findById(id).orElse(new Product());
        return mapper.MAPPER.fromProduct(product);
    }
}
