package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.domain.Bucket;
import com.maksimbalashov.spring.domain.User;
import com.maksimbalashov.spring.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productsIds);
    void addProducts(Bucket bucket, List<Long> productsIds);
    BucketDTO getBucketByUser(String name);
    void commitBucketToOrder(String username);
}
