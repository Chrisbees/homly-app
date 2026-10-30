package com.chrisbees.e_commerce;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.chrisbees.e_commerce.role.Role;
import com.chrisbees.e_commerce.role.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeRoles(
			RoleRepository roleRepository) {

		return args -> {

			List<String> roles = List.of(
					"ROLE_USER",
					"ROLE_AGENT",
					"ROLE_ADMIN");

			for (String roleName : roles) {

				if (roleRepository.findByName(roleName).isEmpty()) {

					Role role = new Role();
					role.setName(roleName);
					role.setCreatedBy("APP");
					roleRepository.save(role);
				}
			}
		};
	}
}
