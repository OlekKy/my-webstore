package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.packt.webstore.domain.Product;

public interface ProductRepository {
	
	List <Product> getAllProducts();
	Product getProductById(String productId);
	List <Product> getProductsByCategory(String category);
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	List <Product> getProductsByManufacturer(String manufacturer); // strona 95
	Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterByPrice);
	Set<Product> getProductsByFilters(String category, Map<String, List<String>> filterByPrice, String manufacturer);
}
