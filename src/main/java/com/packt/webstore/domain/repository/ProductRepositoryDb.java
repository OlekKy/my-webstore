package com.packt.webstore.domain.repository;

import java.util.List;
import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.DatabaseException;

public interface ProductRepositoryDb {

	void createTable() throws DatabaseException;
	List<Product> getAllProducts();
	void addProduct(Product product);
}
