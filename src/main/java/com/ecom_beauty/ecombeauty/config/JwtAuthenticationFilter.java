package com.ecom_beauty.ecombeauty.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom_beauty.ecombeauty.users.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
	        throws ServletException, IOException {
	    String requestTokenHeader = request.getHeader("Authorization");
	    String username = null;
	    String jwtToken = null;
		
	    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	        jwtToken = requestTokenHeader.substring(7);
	        
	        try {
	            username = this.jwtUtils.extractUsername(jwtToken);
	        } catch (ExpiredJwtException expiredJwtException) {
	            LOGGER.warn("El token ha expirado");
	        } catch (Exception e) {
	            LOGGER.error("Error al procesar el token", e);
	        }
	    } else {
	        LOGGER.warn("Token inválido. No empieza con Bearer String. Encabezado: {}", requestTokenHeader);
	    }
		
	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
	        if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
	            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        } else {
	            LOGGER.warn("El token no es válido");
	        }
	    }
	    filterChain.doFilter(request, response);
	}
	
}