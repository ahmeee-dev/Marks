package com.ahmeee_dev.web_serv.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Product {
	
	@Id
	private int Id;
	private String prodName;
	private int price;

	
	public Product() {};

	public Product(int prodID, String prodName, int price) {
		this.Id = prodID;
		this.prodName = prodName;
		this.price = price;
	}

	public void setID(int ID) { this.Id = ID; }
	public void setName(String name) { this.prodName = name; }
	public void setPrice(int price) { this.price = price; }

	public int getID() { return Id; }
	public String getName() { return prodName; }
	public int getPrice() { return price; }

	@Override
	public String toString() {
		return("Product{Id=" + Id + ", name='" + prodName + "', price=" + price + "}");
	}


}
