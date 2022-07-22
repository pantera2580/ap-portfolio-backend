package com.mec.apportfoliobackend.security.jwt;

import com.mec.apportfoliobackend.security.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthService authService;
    @Autowired
    public JwtTokenFilter(AuthService authService, JwtProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);
        if(token!=null && jwtProvider.validateToken(token)) {
            String username = jwtProvider.getUserNameFromToken(token);
            UserDetails userDetails = authService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            logger.error("Fail in filter method");
            e.printStackTrace();
        }
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }

}
