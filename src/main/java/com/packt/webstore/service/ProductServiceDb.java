package com.packt.webstore.service;

import java.util.List;
import com.packt.webstore.domain.Product;

public interface ProductServiceDb {

	void createTable();
	List<Product> getAllProducts();
	void addProduct(Product product);
}
