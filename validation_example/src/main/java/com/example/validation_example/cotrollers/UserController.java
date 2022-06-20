package com.example.validation_example.cotrollers;

import com.example.validation_example.entities.UserEntity;
import com.example.validation_example.entities.dto.UserDto;
import com.example.validation_example.repositories.UserRepository;
import com.example.validation_example.utils.UserCustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserCustomValidator userValidator;

    @InitBinder
    protected void initBinder (final WebDataBinder binder)
    {
        binder.addValidators(userValidator);
    }
    // METODA PRE UBACIVANJA VALIDACIJE PASSWORDA

    /*
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserEntity newUser, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
        }

        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
     */

    // METODA NAKON UBACIVANJA VALIDACIJE PASSWORDA
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserDto newUser, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
        }else{
            userValidator.validate(newUser,result);
        }

        UserEntity user = new UserEntity();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setAge(newUser.getAge());
        userRepository.save(user);

        return new ResponseEntity<>(newUser,HttpStatus.OK);

    }


    private String createErrorMessage(BindingResult result){
        return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
    }
}


