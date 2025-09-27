package com.ahmeee_dev.web_serv.models;

import org.springframework.stereotype.Component;

@Component
public class Product {
	
	private int prodID;
	private String prodName;
	private int price;

	
	public Product() {};

	public Product(int prodID, String prodName, int price) {
		this.prodID = prodID;
		this.prodName = prodName;
		this.price = price;
	}

	public void setID(int ID) { this.prodID = ID; }
	public void setName(String name) { this.prodName = name; }
	public void setPrice(int price) { this.price = price; }

	public int getID() { return prodID; }
	public String getName() { return prodName; }
	public int getPrice() { return price; }

	@Override
	public String toString() {
		return("Product{Id=" + prodID + ", name='" + prodName + "', price=" + price + "}");
	}


}
