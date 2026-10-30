package com.chrisbees.e_commerce.auth.request;

import com.chrisbees.e_commerce.validation.NonDisposableEmail;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class RegistrationRequest {
    
    @NotBlank(message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK")
    @Size(min = 1,
        max = 50,
        message =  "VALIDATION.REGISTRATION.FIRSTNAME.SIZE"
    )
    @Pattern(
        regexp = "^[\\p{L} ']+$",
        message = "VALIDATION.REGISTRATION.FIRSTNAME.PATTERN"
    )
    @Schema(example = "Ali") 
    private String firstName;

     @NotBlank(message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK")
    @Size(min = 1,
        max = 50,
        message =  "VALIDATION.REGISTRATION.LASTNAME.SIZE"
    )
    @Pattern(
        regexp = "^[\\p{L} ']+$",
        message = "VALIDATION.REGISTRATION.LASTNAME.PATTERN"
    )
    @Schema(example = "Ali") 
    private String lastName;

    @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK")
    @Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
    @NonDisposableEmail(message = "VALIDATION.REGISTRATION.EMAIL.DISPOSABLE")
    @Schema(example = "bee@mail.com")
    private String email;

    @NotBlank(message = "VALIDATION.REGISTRATION.PHONE.NOT_BLANK")
    @Pattern(
        regexp = "^\\+?[0-9]{10,13}$",
        message = "VALIDATION.REGISTRATION.PHONE.FORMAT"
    )
    @Schema(example = "+2349673859322") 
    private String phoneNumber;

    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
    @Size(min = 8,
        max = 50,
        message =  "VALIDATION.REGISTRATION.PASSWORD.SIZE"
    )
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$",
        message = "VALIDATION.REGISTRATION.PASSWORD.WEAK"
    )
    @Schema(example = "<PASSWORD>")
    private String password;
    
    @NotBlank(message = "VALIDATION.REGISTRATION.CONFIRM.PASSWORD.NOT_BLANK")
    @Size(min = 8,
        max = 50,
        message =  "VALIDATION.REGISTRATION.CONFIRM.PASSWORD.SIZE"
    )
    @Schema(example = "<PASSWORD>")
    private String confirmPassword;
}
