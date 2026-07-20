package com.chrisbees.e_commerce.images.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrisbees.e_commerce.images.domain.PropertyImage;

public interface ImageRepository extends JpaRepository<PropertyImage, Long>{
   
}
