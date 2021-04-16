package com.lab.library.domain.dto;

import com.lab.library.domain.Role;
import com.lab.library.domain.User;
import lombok.Data;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;

    }
}