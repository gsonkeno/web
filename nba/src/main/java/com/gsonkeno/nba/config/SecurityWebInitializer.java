package com.gsonkeno.nba.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
/*过滤web请求，AbstractSecurityWebApplicationInitializer实现了WebApplicationInitializer
/*因此Spring会发现它，并且用它在Web容器中注册DelegatingFilterProxy
 * DelegatingFilterProxy
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

}
