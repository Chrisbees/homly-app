package com.chrisbees.e_commerce.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository
 */
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String roleUser);
}
