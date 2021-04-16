package com.lab.library.service;

import com.lab.library.domain.User;

public interface AuthService {

    User login(String username, String password);
    User register(User user);
}
