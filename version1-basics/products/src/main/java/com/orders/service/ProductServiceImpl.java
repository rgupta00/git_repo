package com.orders.service;

import com.orders.dto.ProductDto;
import com.orders.repo.Product;
import com.orders.repo.ProductRepo;
import com.orders.util.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepository;
    @Override
    public ProductDto createProduct(ProductDto productRequest) {
        Product product= ConvertUtil.toProduct(productRequest);
        productRepository.save(product);
        return ConvertUtil.toProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ConvertUtil::toProductDto).toList();
    }
}
