package com.chrisbees.e_commerce.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.chrisbees.e_commerce.user.request.ChangePasswordRequest;
import com.chrisbees.e_commerce.user.request.ProfileUpdateRequest;

public interface UserService extends UserDetailsService{

    void updateProfileInfo(ProfileUpdateRequest request, String userId);

    void changePassword(ChangePasswordRequest request, String userId);

    void deactivateAccount(String userId);

    void reactivateAccount(String userId); 

    void deleteAccount(String userId);
    
}
