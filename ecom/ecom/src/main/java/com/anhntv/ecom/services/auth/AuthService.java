package com.anhntv.ecom.services.auth;

import com.anhntv.ecom.dto.SignupRequest;
import com.anhntv.ecom.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
