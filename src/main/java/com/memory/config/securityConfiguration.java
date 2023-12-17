package com.memory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class securityConfiguration {
    @Bean
    SecurityFilterChain userFielter(HttpSecurity http) throws Exception
    {return http
        .csrf(csrf->csrf.disable())
        .authorizeHttpRequests(auth->{
            auth.requestMatchers("/", "","css/**","js/**","/register","asset/**","/customer/register").permitAll();
            auth.anyRequest().authenticated();
        })
        .formLogin(login->{
            login.loginPage("/").permitAll(); 
            login.defaultSuccessUrl("/success");
        })
        .build();        
    }
    @Autowired
     private UserDetailsService service;
    @Bean
    public AuthenticationProvider authProvider()
    {   
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
       // System.out.println(provider.setUserDetailsPasswordService(null););
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

}
