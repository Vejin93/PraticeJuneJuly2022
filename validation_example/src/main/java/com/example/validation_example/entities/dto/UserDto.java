package com.example.validation_example.entities.dto;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
@Table(name="user")
public class UserDto {

    @NotNull(message = "First name must be provided.\n")
    @Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.\n")
    private String firstName;

    @NotNull(message = "Last name must be provided.\n")
    @Size(min=2, max=30, message = "Last name must be between {min} and {max} characters long.\n")
    private String lastName;

    @NotNull(message = "Email must be provided.\n")
    @Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email is not valid.\n")
    private String email;

    @NotNull(message = "Username must be provided.\n")
    @Size(min=5, max=15, message = "Username must be between {min} and {max} characters long.\n")
    private String username;

    @NotNull(message = "Password must be provided.\n")
    @Size(min=5, max=10, message = "Password must be between {min} and {max} characters long.\n")
    //DODATAK DA PASSWORD MORA IMATI JEDNO VELIKO SLOVO, JEDNO MALO I JEDAN BROJ
    @Pattern(regexp = "(?=.[a-z])(?=.[A-Z])(?=.[0-9])")
    private String password;

    @NotNull(message = "Confirmed password must be provided.\n")
    @Size(min=5, max=10, message = "Confirmed password must be between {min} and {max} characters long.\n")
    private String confirmedPassword;

    @NotNull(message = "Age must be provided.\n")
    @Min(value=18, message="Age must be 18 or higher!\n")
    private Integer age;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String username, String password, String confirmedPassword, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
