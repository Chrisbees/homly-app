package com.chrisbees.e_commerce.auth;

import com.chrisbees.e_commerce.auth.request.AuthenticationRequest;
import com.chrisbees.e_commerce.auth.request.RefreshRequest;
import com.chrisbees.e_commerce.auth.request.RegistrationRequest;
import com.chrisbees.e_commerce.auth.response.AuthenticationResponse;

public interface AuthenticationService {
    
    AuthenticationResponse login (AuthenticationRequest request);

    void register(RegistrationRequest request);
     
    AuthenticationResponse refreshToken(RefreshRequest request);
}
