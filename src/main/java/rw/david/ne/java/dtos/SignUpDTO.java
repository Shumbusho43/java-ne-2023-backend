package rw.david.ne.java.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.david.ne.java.security.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String names; // firstName + lastName combined into a single field

    @NotBlank
    @Pattern(regexp = "[0-9]{10}", message = "Your phone is not a valid phone number")
    private String phoneNumber; // Renamed to match the User model
    @Pattern(regexp = "[0-9]{16}",message = "Your national ID is not a valid national ID")
    @NotBlank
    private String nationalId;
    @ValidPassword
    @NotBlank
    private String password;

}