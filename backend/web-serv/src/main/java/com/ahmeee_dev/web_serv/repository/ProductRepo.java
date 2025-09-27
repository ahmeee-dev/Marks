package com.ahmeee_dev.web_serv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmeee_dev.web_serv.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {


}