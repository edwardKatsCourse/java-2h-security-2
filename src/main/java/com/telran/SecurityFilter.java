package com.telran;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//1,                            50
public class SecurityFilter extends OncePerRequestFilter {

    private static final String VALID_TOKEN = "valid_token";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");

        System.out.println("Security filter: enter");
        if (header != null) {
            System.out.println("Security filter: header 'Authorization'");

            if (header.equals(VALID_TOKEN)) { //userSessionRepository.findByToken(header);

                System.out.println("Security filter: found valid token");
                //GETTING >>KEY<< HERE!!!!!


                //>>KEY<<   -  interface Authentication

                String usernameForController = httpServletRequest.getHeader("username");

                if (usernameForController == null) {
                    usernameForController = "no name";
                }

                Authentication key = new UsernamePasswordAuthenticationToken(
                        usernameForController,
                        null,
                        new ArrayList<>() //roles
                );

                //Put your key here!
                SecurityContextHolder.getContext().setAuthentication(key);
            }


        }

        System.out.println("Security filter: exit");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
