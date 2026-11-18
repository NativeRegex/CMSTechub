package com.cmstechub.resume.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityHeadersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Prevent clickjacking
        httpResponse.setHeader("X-Frame-Options", "DENY");

        // Prevent MIME-type sniffing
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");

        // XSS protection (legacy browsers)
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");

        // Referrer policy
        httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");

        // Content Security Policy (allow Google Fonts, self, inline styles)
        httpResponse.setHeader("Content-Security-Policy",
                "default-src 'self'; " +
                "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; " +
                "font-src 'self' https://fonts.gstatic.com; " +
                "img-src 'self' data:; " +
                "script-src 'self' 'unsafe-inline'; " +
                "connect-src 'self';");

        // HSTS — force HTTPS for 1 year (only meaningful over HTTPS)
        httpResponse.setHeader("Strict-Transport-Security",
                "max-age=31536000; includeSubDomains");

        chain.doFilter(request, response);
    }
}
