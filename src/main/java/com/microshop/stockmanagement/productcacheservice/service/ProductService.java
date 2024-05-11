package com.microshop.stockmanagement.productcacheservice.service;

import com.microshop.stockmanagement.productcacheservice.enums.Language;
import com.microshop.stockmanagement.productcacheservice.repository.entity.Product;

public interface ProductService {

    Product getProduct(Language language, Long productId);

    void deleteProducts(Language language);
}
