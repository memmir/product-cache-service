package com.microshop.stockmanagement.productcacheservice.feign.product;

import com.microshop.stockmanagement.productcacheservice.enums.Language;
import com.microshop.stockmanagement.productcacheservice.response.InternalApiResponse;
import com.microshop.stockmanagement.productcacheservice.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("${feign.product-service.name}")
public interface ProductServiceFeignClient {

    @GetMapping(value = "/api/1.0/product/{language}/get/{productId}")
    InternalApiResponse<ProductResponse> getProduct(@PathVariable("language") Language language, @PathVariable("productId") Long productId);
}
