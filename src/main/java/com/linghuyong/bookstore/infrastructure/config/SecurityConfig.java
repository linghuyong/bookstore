package com.linghuyong.bookstore.infrastructure.config;

import com.linghuyong.bookstore.infrastructure.security.JwtAuthenticationTokenFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity()
@Configuration
public class SecurityConfig {
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authz -> authz
                        // 注册接口，允许匿名
                        .requestMatchers("/api/user/register")
                        .anonymous()
                        // 登录登出接口，允许匿名
                        .requestMatchers("/api/user/login")
                        .anonymous()
                        .requestMatchers("/api/user/logout")
                        .anonymous()
                        .requestMatchers("/admin/**").hasAuthority("admin")
                        .requestMatchers("/api/**").hasAuthority("user")
                        .anyRequest()
                        .permitAll()
                )
                .httpBasic(withDefaults());
        return http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
