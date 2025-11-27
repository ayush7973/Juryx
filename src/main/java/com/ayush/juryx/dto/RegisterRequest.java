package com.ayush.juryx.dto;

import com.ayush.juryx.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Role role;
}
