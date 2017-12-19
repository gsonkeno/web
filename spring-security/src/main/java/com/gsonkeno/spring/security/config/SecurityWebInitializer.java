package com.gsonkeno.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * DelegatingFilterProxy
 */
@Configuration
@EnableWebSecurity
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

}
