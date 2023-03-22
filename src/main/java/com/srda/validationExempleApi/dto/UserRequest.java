package com.srda.validationExempleApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
// the generated constructor will be private, and an additional static 'constructor' is generated with the same argument list that wraps the real constructor.
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
//  On fait les validation dans le dto.
//    1.0 Première validation, évite le null.
    @NotNull(message = "username shouldt not be null")
    private String name;
    @Email(message = "email invalide")
    private String email;
    @NotNull
    @Pattern(regexp = "^\\d{10}$" ,message = "numéro invalide")
    private String mobile;
    private String gender;
    @Min(18)
    @Max(60)
    private int age;
    //Not blank combine notNull et not Empty.
    @NotBlank
    private String nationality;

}

