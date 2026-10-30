package com.chrisbees.e_commerce.property.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chrisbees.e_commerce.property.domain.Property;
import com.chrisbees.e_commerce.user.User;
import com.chrisbees.e_commerce.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyAuthorizationService {

    private final UserRepository userRepository;

    public void checkOwnership(Property property) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()) {
            throw new AccessDeniedException(
                    "Authentication is required");
        }

        User currentUser = userRepository
                .findByEmailIgnoreCase(authentication.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        boolean isOwner = property.getOwner() != null
                && property.getOwner().getId()
                        .equals(currentUser.getId());

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        "ROLE_ADMIN".equals(authority.getAuthority()));

        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException(
                    "You are not authorized to modify this property");
        }
    }
}