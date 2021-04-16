package com.lab.library.service;

import com.lab.library.domain.Role;
import com.lab.library.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
