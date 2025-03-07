package com.orders.util;

import com.orders.dto.ProductDto;
import com.orders.repo.Product;

public class ConvertUtil {
    public static Product toProduct(ProductDto productDto) {
        Product product = Product.builder()
        		.id(productDto.getId())
        		.name(productDto.getName())
        		.description(productDto.getDescription())
        		.price(productDto.getPrice())
        		.build();
        return product;

    }
    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = ProductDto.builder()
        		.id(product.getId())
        		.name(product.getName())
        		.description(product.getDescription())
        		.price(product.getPrice())
        		.build();
        return productDto;

    }
}
