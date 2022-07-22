package com.mec.apportfoliobackend.security.user;

public interface IUserService {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    UserResponse save(UserRequest userRequest);
    LoginResponse login(LoginRequest loginRequest);
}
