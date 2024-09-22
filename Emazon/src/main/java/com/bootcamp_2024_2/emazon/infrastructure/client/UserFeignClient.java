package com.bootcamp_2024_2.emazon.infrastructure.client;

import com.bootcamp_2024_2.emazon.application.dto.request.LoginRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "emazon-auth-service", url = "http://localhost:8081", configuration = FeignClientConfig.class)
public interface UserFeignClient {

    @PostMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(LoginRequest loginRequest);

}
