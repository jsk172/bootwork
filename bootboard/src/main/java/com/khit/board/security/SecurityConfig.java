package com.khit.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //@Bean은 프로젝트에서 관리가 안되는 클래스를 번호로 사용할때 필요함
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.build();
    }
}
