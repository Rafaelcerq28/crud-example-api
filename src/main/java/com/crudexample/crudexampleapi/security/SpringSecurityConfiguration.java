package com.crudexample.crudexampleapi.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    /*
     * 
     * CONFIGURANDO A SEGURANCA DA API
     * Antes dessa configuracao na nao era possivel fazer post na api por conta dos filtros de seguranca
     */
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.authorizeRequests(
            auth -> auth.anyRequest().authenticated());

            http.httpBasic(withDefaults());
            http.csrf().disable();
        return http.build();
    }

}
