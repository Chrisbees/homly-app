package com.chrisbees.e_commerce.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisbees.e_commerce.auth.AuthenticationService;
import com.chrisbees.e_commerce.auth.request.AuthenticationRequest;
import com.chrisbees.e_commerce.auth.request.RefreshRequest;
import com.chrisbees.e_commerce.auth.request.RegistrationRequest;
import com.chrisbees.e_commerce.auth.response.AuthenticationResponse;
import com.chrisbees.e_commerce.exception.BusinessException;
import com.chrisbees.e_commerce.exception.ErrorCode;
import com.chrisbees.e_commerce.role.Role;
import com.chrisbees.e_commerce.role.RoleRepository;
import com.chrisbees.e_commerce.security.JwtService;
import com.chrisbees.e_commerce.user.User;
import com.chrisbees.e_commerce.user.UserMapper;
import com.chrisbees.e_commerce.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final User user = (User) auth.getPrincipal();
        final String token = this.jwtService.generateAccessToken(user.getUsername());
        final String refreshToken = this.jwtService.generateRefreshToken(user.getUsername());
        final String tokenType = "Bearer ";

        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .build();

    }

    @Override
    @Transactional
    public void register(RegistrationRequest request) {

        checkPasswords(
                request.getPassword(),
                request.getConfirmPassword());

        checkUserEmail(request.getEmail());
        checkUserPhoneNumber(request.getPhoneNumber());

        final Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException(
                        "Role user does not exist"));

        final User user = userMapper.toUser(request);

        user.setRoles(new ArrayList<>(List.of(userRole)));

        userRepository.save(user);
    }

    // @Override
    // @Transactional
    // public void register(RegistrationRequest request) {
    // checkUserEmail(request.getEmail());
    // checkUserPhoneNumber(request.getPhoneNumber());
    // checkPasswords(request.getPassword(), request.getConfirmPassword());

    // final Role userRole = this.roleRepository.findByName("ROLE_USER")
    // .orElseThrow(() -> new EntityNotFoundException("Role user does not exist"));

    // final List<Role> roles = new ArrayList<>();
    // roles.add(userRole);

    // final User user = this.userMapper.toUser(request);
    // user.setRoles(roles);
    // log.debug("Saving user {}", user);
    // this.userRepository.save(user);

    // final List<User> users = new ArrayList<>();
    // users.add(user);
    // userRole.setUsers(users);

    // this.roleRepository.save(userRole);
    // }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        final String newAccessToken = this.jwtService.refreshAccessToken(request.getRefreshToken());
        final String tokenType = "Bearer ";
        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .tokenType(tokenType)
                .build();
    }

    private void checkPasswords(String password, String confirmPassword) {
        if (password == null || !password.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
    }

    private void checkUserPhoneNumber(String phoneNumber) {
        final boolean phoneNumberExists = this.userRepository.existsByPhoneNumber(phoneNumber);
        if (phoneNumberExists) {
            throw new BusinessException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
    }

    private void checkUserEmail(String email) {
        final boolean emailExists = this.userRepository.existsByEmailIgnoreCase(email);
        if (emailExists) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }
}
