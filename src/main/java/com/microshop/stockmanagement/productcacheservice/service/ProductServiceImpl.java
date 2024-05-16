package com.microshop.stockmanagement.productcacheservice.service;

import com.microshop.stockmanagement.productcacheservice.enums.Language;
import com.microshop.stockmanagement.productcacheservice.feign.product.ProductServiceFeignClient;
import com.microshop.stockmanagement.productcacheservice.repository.ProductRepository;
import com.microshop.stockmanagement.productcacheservice.repository.entity.Product;
import com.microshop.stockmanagement.productcacheservice.response.ProductResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductServiceFeignClient productServiceFeignClient;

    @Override
    public Product getProduct(Language language, Long productId) {
        Product product;

        try {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if(optionalProduct.isPresent()){ //Burada gelen datanının içinin dolu olup olmadığını test ediyoruz.
                product = optionalProduct.get();
                log.info("Veri  redis server dan getirildi");
                //Veri cache den getirldi. Yani Redis den getirldi.
            }else { // Eğer veri yoksa productservice projesinden çekeceğiz veriyi ve sonra redise kaydedeceğiz.
                log.info("Veri  product service den getirildi");
                product = new Product();
                ProductResponse response = productServiceFeignClient.getProduct(language, productId).getPayload(); //BURADA PAYLOAD I getir diyerek sadece product nesnesini getir demek istedik.

                product.setProductId(response.getProductId());
                product.setProductName(response.getProductName());
                product.setPrice(response.getPrice());
                product.setQuantity(response.getQuantity());
                product.setProductCreatedDate(response.getProductCreatedDate());
                product.setProductUpdatedDate(response.getProductUpdatedDate());

                productRepository.save(product);

            }
        }catch (FeignException.FeignClientException.NotFound e){
            throw new NotFoundException("Urun bulunamadi" + productId);

        }

        return product;
    }

    @Override
    public void deleteProducts(Language language) {
        productRepository.deleteAll();
        log.info("Urunler silindi.");
    }
}
