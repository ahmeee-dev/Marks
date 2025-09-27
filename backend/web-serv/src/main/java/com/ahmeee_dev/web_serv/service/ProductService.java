package com.ahmeee_dev.web_serv.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ahmeee_dev.web_serv.models.Product;

@Service
public class ProductService {
	
	List<Product> products = new ArrayList<>(Arrays.asList(
		new Product(1, "Camera", 700),
		new Product(2, "Glasses", 150),
		new Product(3, "statue", 50000)));

	public List<Product> getProducts() { return products; }

	public Product getProductById(int productId) {
		return products.stream()
			.filter(p -> p.getID() == productId)
			.findFirst().get();
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public void updateProduct(Product product) {
		int index = -1;
		for (int i = 0; i < products.size(); i++) {
			if (product.getID() == products.get(i).getID()) {
				index = i;
			}
		}
		this.products.set(index, product);
	}

	public void deleteProduct(int Id) {
		int index = -1;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getID() == Id) {
				index = i;
			}
		}
		products.remove(index);
	}
}
