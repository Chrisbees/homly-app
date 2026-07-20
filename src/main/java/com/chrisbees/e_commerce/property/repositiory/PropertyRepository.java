package com.chrisbees.e_commerce.property.repositiory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chrisbees.e_commerce.property.domain.Property;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

    Optional<Property> findByUuidAndDeletedFalse(String uuid);
    Page<Property> findAllByDeletedFalse(Pageable pageable);
    void deleteByUuid(String uuid);
    
}
