package com.ecom_beauty.ecombeauty.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_beauty.ecombeauty.config.JwtUtils;
import com.ecom_beauty.ecombeauty.users.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            auth(jwtRequest.getEmail(), jwtRequest.getPassword());
        } catch (Exception e) {
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }
	
	private void auth(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USUARIO DESHABILITADO: " + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Credenciales invalidas: " + badCredentialsException.getMessage());
        }
    }
}
