package com.ecom_beauty.ecombeauty.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_beauty.ecombeauty.config.JwtUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserSignupDTO userSignupDTO) throws Exception {    	
    	// Check if the email exists
        if (userService.getUserByEmail(userSignupDTO.getEmail()) != null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "El email ya existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

    	User newUser = new User();
        newUser.setPasswordHash(new BCryptPasswordEncoder().encode(userSignupDTO.getPasswordHash()));
        newUser.setFirstName(userSignupDTO.getFirstName());
        newUser.setLastName(userSignupDTO.getLastName());
        newUser.setEmail(userSignupDTO.getEmail());
        newUser.setProfilePhotoUrl(userSignupDTO.getProfilePhotoUrl());

        // Save new user
        User createdUser = userService.saveUser(newUser);

        // Generate token for the new user
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(createdUser.getEmail());
        String token = jwtUtils.generateToken(userDetails);

        Map<String, Object> response = new HashMap<>();
        response.put("user", createdUser);
        response.put("token", token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        try {
            Optional<User> user = userService.getUserById(Integer.parseInt(id));
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Usuario no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (NumberFormatException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "ID de usuario inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // Update user
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        if (user != null) {
            user.setFirstName(userUpdateDTO.getFirstName());
            user.setLastName(userUpdateDTO.getLastName());
            user.setProfilePhotoUrl(userUpdateDTO.getProfilePhotoUrl());
            
            if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
                user.setPasswordHash(new BCryptPasswordEncoder().encode(userUpdateDTO.getPassword()));
            }
            
            User updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Usuario no encontrado"));
        }
    }

    // Delete user
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        if (user != null) {
            userService.deleteUser(user.getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Usuario no encontrado"));
        }
    }
}