package com.example.validation_example.cotrollers;

import com.example.validation_example.entities.UserEntity;
import com.example.validation_example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserEntity newUser, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
        }

        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
    private String createErrorMessage(BindingResult result){
        return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
    }
}


