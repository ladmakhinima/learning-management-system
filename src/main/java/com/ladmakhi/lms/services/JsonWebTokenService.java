package com.ladmakhi.lms.services;

import com.ladmakhi.lms.models.User;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JsonWebTokenService {
    String generateToken(User user);

    boolean verifyToken(String token);

    SecretKey generateSignKey();

    Date getExpirationTime();
}
