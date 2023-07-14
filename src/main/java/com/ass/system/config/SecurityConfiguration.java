package com.ass.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider, LogoutHandler logoutHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf()
////                .disable()
////                .authorizeHttpRequests()
////                .requestMatchers("/api/v1/user/register", "/api/v1/demo-controller")
////                .permitAll();
//                //authenticate these
////                .anyRequest()
////                .authenticated()
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authenticationProvider(authenticationProvider)
////                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
//    }

}

