package com.xiaoluomo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$wNxNRe0gJBTsLJtLAU0Mb.DCcSBlLbslMZY4JI2DW4MMtGjYsE8a2")
                .roles("admin")
                .and()
                .withUser("sang")
                .password("$2a$10$wNxNRe0gJBTsLJtLAU0Mb.DCcSBlLbslMZY4JI2DW4MMtGjYsE8a2")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /** oauth/** 模式直接放行*/
        http.antMatcher("/oauth/**")
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .and()
                .csrf()
                .disable();


    }
}
