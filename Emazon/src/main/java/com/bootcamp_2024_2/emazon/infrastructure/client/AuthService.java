package com.bootcamp_2024_2.emazon.infrastructure.client;

import com.bootcamp_2024_2.emazon.application.dto.request.LoginRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserFeignClient userFeignClient;

    public LoginResponse login (LoginRequest loginRequest) {
        return userFeignClient.login(loginRequest);
    }
}
