package com.rohit.ElectronicStore.dtos;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {


    private String userId;

    //here if the name size does not fall in this give  range then this message will bw thrown to te user.
    @Size(min = 3, max = 20, message = "Invalid name")
    private String name;


    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid User Email")
    @NotBlank(message = "Invalid User Email")
    private String email;


    @NotBlank(message = "Password Required")
    private String password;


    @Size(min = 4, max = 6, message = "Invalid Gender")
    private String gender;


    @NotBlank(message = "Required Field")
    private String about;


}
