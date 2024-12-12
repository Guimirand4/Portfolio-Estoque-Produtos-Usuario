package com.remedios.guilherme.curso.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .requestMatchers(HttpMethod.POST, "/login").permitAll() 
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**","/prod/login","/login", "/v3/prod-docs/**", "/swagger-ui/index.html", "/actuator/**","/prod/swagger-ui/**", "/prod/v3/api-docs/**", "/prod/swagger-resources/**", "/prod/webjars/**").permitAll() // Libera o Swagger e Actuator
                .anyRequest().authenticated() 
            .and()
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de segurança
            .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
