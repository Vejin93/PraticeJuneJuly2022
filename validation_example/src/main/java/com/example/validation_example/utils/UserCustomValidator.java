package com.example.validation_example.utils;

import com.example.validation_example.entities.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserCustomValidator implements Validator {

    @Override
    public boolean supports(Class<?> myClass){
        return UserDto.class.equals(myClass);
    }

    @Override
    public void validate(Object target, Errors errors){
        UserDto user = (UserDto) target;

        if(!user.getPassword().equals(user.getConfirmedPassword())){
            errors.reject("404","Password must be the same!");
        }
    }

}
