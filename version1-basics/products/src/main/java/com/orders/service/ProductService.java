package com.orders.service;

import com.orders.dto.ProductDto;

import java.util.List;

public interface ProductService{
	public ProductDto createProduct(ProductDto productRequest);
	public List<ProductDto> getAllProducts();
}
