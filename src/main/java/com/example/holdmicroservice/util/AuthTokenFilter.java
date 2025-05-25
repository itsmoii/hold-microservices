package com.example.holdmicroservice.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthTokenFilter implements Filter {

    @Value("${internal.secret}")
    private String internalSecret;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer " + internalSecret)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            res.getWriter().write("{\"error\": \"Unauthorized: Invalid or missing token\"}");
            res.getWriter().flush();
            res.getWriter().close();
            return;
        }

        chain.doFilter(request, response);


    }

}
