package com.holitor.holitorservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.holitor.holitorservice.module.user.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class HolitorSecurityConfiguration {
  
  @Autowired UserDetailsServiceImpl userDetailsService;
      
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    http.csrf(csrf -> csrf.disable())
        .cors(cors -> corsConfigurationSource())
        .authorizeHttpRequests()
        .antMatchers("/api-holitor/sale-points").permitAll()
        .antMatchers("/api-holitor/posts").permitAll()
        .antMatchers("/api-holitor/categories").permitAll()
        .antMatchers("/api-holitor/images").permitAll()
        .antMatchers("/api-holitor/products").permitAll()
        .antMatchers("/api-holitor/storages").permitAll()
        .antMatchers("/api-holitor/units").permitAll()
        .antMatchers("/api-holitor/vegetables").permitAll()
        .antMatchers("/api-holitor/botanical-families").permitAll()
        .antMatchers("/api-holitor/add-user").permitAll()
        .anyRequest().authenticated().and()
        .authenticationManager(authenticationManager)
        .httpBasic(withDefaults());
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

// @Configuration
// @EnableWebSecurity
// public class HolitorSecurityConfiguration extends WebSecurityConfigurerAdapter {

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest()
// 				.authenticated().and()
// 				// .formLogin().and()
// 				.httpBasic();
// 	}
	
// }

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)  
// public class HolitorSecurityConfiguration {

//   @Bean
//   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     http.cors(cors -> corsConfigurationSource()).authorizeHttpRequests()
//         .antMatchers("/api-holitor/sale-points").permitAll()
//         .antMatchers("/api-holitor/posts").permitAll()
//         .antMatchers("/api-holitor/categories").permitAll()
//         .antMatchers("/api-holitor/images").permitAll()
//         .antMatchers("/api-holitor/products").permitAll()
//         .antMatchers("/api-holitor/storages").permitAll()
//         .antMatchers("/api-holitor/units").permitAll()
//         .antMatchers("/api-holitor/vegetables").permitAll()
//         .antMatchers("/api-holitor/botanical-families").permitAll()
//         .anyRequest().authenticated().and()
//         .oauth2ResourceServer(server -> server.jwt());
//     return http.build();
//   }

//   @Bean
//   CorsConfigurationSource corsConfigurationSource() {
//     CorsConfiguration corsConfig = new CorsConfiguration();
//     corsConfig.addAllowedOrigin("*");
//     corsConfig.addAllowedHeader("*");
//     corsConfig.addAllowedMethod("*");
//     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", corsConfig);
//     return source;
//   }

// }