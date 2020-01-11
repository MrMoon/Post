package com.moon.squad.configuration.security;

import com.moon.squad.configuration.security.jwt.JwtConfigurer;
import com.moon.squad.configuration.security.jwt.JwtUtil;
import com.moon.squad.service.user.implementation.UserServiceImp;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import static com.moon.squad.shared.ApplicationConstants.API;
import static com.moon.squad.shared.ApplicationConstants.AUTH_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SIGN_IN;
import static com.moon.squad.shared.ApplicationConstants.SIGN_UP;
import static com.moon.squad.shared.ApplicationConstants.UNAUTHORIZED;
import static com.moon.squad.shared.ApplicationConstants.USER;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtUtil jwtUtil;

    public WebSecurityConfiguration(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(12));
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and().authorizeRequests()
                .antMatchers(AUTH_MAPPING + SIGN_UP).permitAll()
                .antMatchers(AUTH_MAPPING + SIGN_IN).permitAll()
                .antMatchers(API + "/**").hasAuthority(USER).anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> httpServletResponse
                .sendError(SC_UNAUTHORIZED, UNAUTHORIZED))
                .and().apply(new JwtConfigurer(jwtUtil));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Nullable
    @Bean
    @Contract (pure = true)
    public UserDetailsService mongoUserDetails() {
        return new UserServiceImp();
    }
}
