package com.vibevault.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vibevault.productservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
