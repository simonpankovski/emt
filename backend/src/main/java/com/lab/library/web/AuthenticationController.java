package com.lab.library.web;

import com.lab.library.config.JWTAuthConstants;
import com.lab.library.domain.User;
import com.lab.library.exceptions.InvalidUserCredentialsException;
import com.lab.library.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.lab.library.config.JWTAuthConstants.TOKEN_PREFIX;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;


    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser( @RequestBody String username, @RequestBody String password){
        User user = null;
        try{
            user = this.authService.login(username, password);

            return ResponseEntity.ok("success");

        }
        catch (InvalidUserCredentialsException exception) {

            throw new InvalidUserCredentialsException();
        }





    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){


        User newUser = authService.register(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
