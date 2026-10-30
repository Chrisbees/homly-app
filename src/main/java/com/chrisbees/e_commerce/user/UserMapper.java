package com.chrisbees.e_commerce.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chrisbees.e_commerce.auth.request.RegistrationRequest;
import com.chrisbees.e_commerce.user.request.ProfileUpdateRequest;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void mergeUserInfo(User savedUser, ProfileUpdateRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName())
                && !savedUser.getFirstName().equals(request.getFirstName())) {
            savedUser.setFirstName(request.getFirstName());
        }

        if (StringUtils.isNotBlank(request.getLastName())
                && !savedUser.getLastName().equals(request.getLastName())) {
            savedUser.setLastName(request.getLastName());
        }

        if (request.getDateOfBirth() != null
                && !request.getDateOfBirth().equals(savedUser.getDateOfBirth())) {
            savedUser.setDateOfBirth(request.getDateOfBirth());
        }
    }

    public User toUser(RegistrationRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .locked(false)
                .credentialsExpired(false)
                .emailVerified(false)
                .phoneVerified(false)
                .build();
    }
}
