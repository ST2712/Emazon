package com.bootcamp_2024_2.emazon.infrastructure.configuration.security;

import com.bootcamp_2024_2.emazon.application.dto.response.UserResponse;
import com.bootcamp_2024_2.emazon.infrastructure.client.UserFeignClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserFeignClient userServiceFeignClient;

    public JwtAuthenticationFilter(JwtService jwtService, UserFeignClient userServiceFeignClient) {
        this.jwtService = jwtService;
        this.userServiceFeignClient = userServiceFeignClient;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            UserResponse userResponse = this.userServiceFeignClient.getUserByEmail(userEmail);

            UserDetails userDetails = new CustomUserDetails(userResponse);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        (userDetails).getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}



