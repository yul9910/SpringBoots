package com.spring_boots.spring_boots.category.repository;

import com.spring_boots.spring_boots.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}
