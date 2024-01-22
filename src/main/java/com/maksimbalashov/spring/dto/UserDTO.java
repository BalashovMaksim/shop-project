package com.maksimbalashov.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
    private boolean activated;
}
