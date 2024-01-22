package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.domain.User;
import com.maksimbalashov.spring.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {  //security
    boolean save(UserDTO userDTO);
    List<UserDTO> getAll();
    void save(User user);
    User findByName(String name);
    void updateProfile(UserDTO userDTO);
    boolean activateUser (String activateCode);
}
