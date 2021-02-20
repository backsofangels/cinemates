package com.salvatore.cinemates.filters;

import com.salvatore.cinemates.utils.JwtConstants;
import com.salvatore.cinemates.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(JwtConstants.JWT_TOKEN_HEADER);
        UserDetails details = null;

        if (authorizationHeader != null) {
            String jwtToken = authorizationHeader.replace("Bearer ", "");
            details = tokenUtils.getUserDetailsFromToken(jwtToken);

            if(jwtToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (tokenUtils.validateToken(jwtToken, details)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            details,
                            null,
                            new ArrayList<>()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }


        chain.doFilter(request, response);
    }
}
