package com.ahmeee_dev.web_serv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmeee_dev.web_serv.models.Product;
import com.ahmeee_dev.web_serv.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo repo;
	
	public List<Product> getProducts() { return repo.findAll(); }

	public Product getProductById(int productId) {
		return repo.findById(productId).orElse(new Product());
	}

	public void addProduct(Product product) {
		repo.save(product);
	}

	public void updateProduct(Product product) {
		repo.save(product);
	}

	public void deleteProduct(int Id) {
		repo.deleteById(Id);
	}
}
