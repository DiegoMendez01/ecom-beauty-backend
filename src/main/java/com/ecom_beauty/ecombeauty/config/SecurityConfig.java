package com.ecom_beauty.ecombeauty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig
{
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizeHandler;
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter)
	{
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.disable())
			.authorizeHttpRequests(authorize -> authorize
				// Public EndPoints
				.requestMatchers("/api/v1/health/", "/api/v1/generate-token", "/api/v1/users/signup", "/api/v1/products/", "/api/v1/products/{id}").permitAll()
				.requestMatchers(
	                    // Swagger UI v2
	                    "/v2/api-docs",
	                    "/swagger-resources",
	                    "/swagger-resources/**",
	                    "/configuration/ui",
	                    "/configuration/security",
	                    "/swagger-ui.html",
	                    "/webjars/**",
	                    // Swagger UI v3 (OpenAPI)
	                    "/v3/api-docs/**",
	                    "/swagger-ui/**"
	            ).permitAll()
				.requestMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated()
			)
			.exceptionHandling(exception -> exception
	            .authenticationEntryPoint(unauthorizeHandler)
	        )
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        );
		
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
}