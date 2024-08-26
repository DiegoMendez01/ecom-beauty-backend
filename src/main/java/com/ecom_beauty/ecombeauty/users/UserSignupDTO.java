package com.ecom_beauty.ecombeauty.users;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupDTO
{
	@NotBlank(message = "El email no puede estar vacio")
    private String email;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Column(name = "last_name")
    private String lastName;
    
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Column(name = "password_hash")
    private String passwordHash;
    
    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;
}
