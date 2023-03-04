package miu.videokabbee.domain;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    @Id
    private Long id;
    @NotBlank(message="first name cant be null")
    private String firstName;
    @NotBlank(message = "last name  should  not be null")
    private String lastName;
    @Digits(integer = 2, fraction = 0, message = "Age must be a 1 or 2-digit number")
    private int age;
    private String role;
    @NotNull(message="username should not be null")
    @Size(max=20,message = "username can not be more than 20")
    private String userName;
   @Size( min=8 ,max=10 ,message = "password should be between 8 and 10")
   @NotBlank(message="password should not be blank")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
   private String password;
    private Contact contact;
    private Address address;
}
