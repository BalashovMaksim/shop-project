package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.domain.User;

public interface MailSenderService {
    void sendActivateCode(User user);
}
