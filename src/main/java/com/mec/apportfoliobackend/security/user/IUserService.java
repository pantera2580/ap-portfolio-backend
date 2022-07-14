package com.mec.apportfoliobackend.security.user;

public interface IUserService {
    User findByUsername(String username);
}
