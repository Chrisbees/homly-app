package com.chrisbees.e_commerce.category.repositiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chrisbees.e_commerce.category.domain.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByUuid(String uuid);
    Optional<Category> findByname(String name);
    
}
