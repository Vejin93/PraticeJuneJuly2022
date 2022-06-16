package com.example.validation_example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue
    Integer id;

    @NotNull(message = "First name must be provided.")
    @Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
    private String firstName;

    @NotNull(message = "Last name must be provided.")
    @Size(min=2, max=30, message = "Last name must be between {min} and {max} characters long.")
    private String lastName;

    @NotNull(message = "Email must be provided.")
    @Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email is not valid.")
    private String email;

    @NotNull(message = "Username must be provided.")
    @Size(min=5, max=15, message = "Username must be between {min} and {max} characters long.")
    private String username;

    @NotNull(message = "Password must be provided.")
    @Size(min=5, max=10, message = "Password must be between {min} and {max} characters long.")
    private String password;

    @NotNull(message = "Age must be provided.")
    @Min(value=18, message="Age must be 18 or higher!")
    private Integer age;

    public UserEntity() {
    }
}
