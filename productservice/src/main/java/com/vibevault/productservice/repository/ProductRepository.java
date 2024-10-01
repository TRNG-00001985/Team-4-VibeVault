package com.vibevault.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.vibevault.productservice.model.Category;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.model.Wishlist;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

//	 List<Product> findProductByUserId(String userId);
	 List<Product> findByCategoryCategoryId(Long categoryId);
}
