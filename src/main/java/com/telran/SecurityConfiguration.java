package com.telran;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() /*1. Removed HTML validation*/
                .sessionManagement() /*2. We manage session, NOT Spring Security*/
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests() /*3. All requests must pass authentication! */
                    .antMatchers("/non-secured").permitAll() /*3.1. Except this*/
                    .antMatchers("/secured").authenticated();/*3.2. Including this*/



        //ANT MATCHER = URL + Security Type (permit, authenticated, deny)


        //>>OUR<< filter before Username/password validation in Spring Security
        http.addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
