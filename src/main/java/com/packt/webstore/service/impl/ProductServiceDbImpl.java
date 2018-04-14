package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepositoryDb;
import com.packt.webstore.service.ProductServiceDb;

@Service
public class ProductServiceDbImpl implements ProductServiceDb {

	@Autowired
	ProductRepositoryDb productRepositoryDb;
	
	public void createTable() {
		productRepositoryDb.createTable();
	}

	public List<Product> getAllProducts() {
		return productRepositoryDb.getAllProducts();
	}

	public void addProduct(Product product) {
		productRepositoryDb.addProduct(product);
	}

}
