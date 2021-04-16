package com.lab.library.service;

import com.lab.library.domain.User;
import com.lab.library.exceptions.InvalidArgumentsException;
import com.lab.library.exceptions.InvalidUserCredentialsException;
import com.lab.library.exceptions.UsernameAlreadyExistsException;
import com.lab.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService= userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(User user) {
        if(this.userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException(user.getUsername());

        User u = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),user.getName(),user.getSurname(),user.getRole());
        return userRepository.save(u);

    }
}
