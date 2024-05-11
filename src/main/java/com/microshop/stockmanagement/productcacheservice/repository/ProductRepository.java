package com.microshop.stockmanagement.productcacheservice.repository;

import com.microshop.stockmanagement.productcacheservice.entity.Product;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
