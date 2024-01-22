package com.maksimbalashov.spring.dao;

import com.maksimbalashov.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
    User findFirstByActiveCode(String activateCode);
}
