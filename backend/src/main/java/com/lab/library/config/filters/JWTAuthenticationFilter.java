package com.lab.library.config.filters;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.library.config.JWTAuthConstants;
import com.lab.library.domain.User;
import com.lab.library.domain.dto.UserDetailsDto;
import com.lab.library.exceptions.PasswordsDoNotMatchException;
import com.lab.library.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User creds = null;
        try {
            creds = new ObjectMapper().readValue(request.getInputStream(), User.class);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (creds ==null) {
            throw new UserNotFoundException("Invalid credentials");
        }
        System.out.println("reached 1");
        UserDetails userDetails = userDetailsService.loadUserByUsername(creds.getUsername());
        if (!passwordEncoder.matches(creds.getPassword(),userDetails.getPassword())) {
            throw new PasswordsDoNotMatchException();
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(),creds.getPassword(),userDetails.getAuthorities()));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User userDetails = (User) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserDetailsDto.of(userDetails)))
                .withExpiresAt(new Date(System.currentTimeMillis()+ JWTAuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET.getBytes()));
        response.addHeader(JWTAuthConstants.HEADER_STRING,JWTAuthConstants.TOKEN_PREFIX+token);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");

        response.getWriter().append(token);

        System.out.println("reached");
    }
}
