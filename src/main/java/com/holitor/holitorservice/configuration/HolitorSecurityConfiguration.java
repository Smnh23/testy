package com.holitor.holitorservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class HolitorSecurityConfiguration {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> corsConfigurationSource()).authorizeHttpRequests()
        .antMatchers("/api-holitor/sale-points").permitAll()
        .antMatchers("/api-holitor/posts").permitAll()
        .antMatchers("/api-holitor/categories").permitAll()
        .antMatchers("/api-holitor/images").permitAll()
        .antMatchers("/api-holitor/products").permitAll()
        .antMatchers("/api-holitor/storages").permitAll()
        .antMatchers("/api-holitor/units").permitAll()
        .antMatchers("/api-holitor/vegetables").permitAll()
        .antMatchers("/api-holitor/botanical-families").permitAll()
        .anyRequest().authenticated().and()
        .oauth2ResourceServer(server -> server.jwt());
    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.addAllowedOrigin("*");
    corsConfig.addAllowedHeader("*");
    corsConfig.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return source;
  }

}