package com.example.serialization.controllers;

import com.example.serialization.controllers.util.RESTError;
import com.example.serialization.entities.AddressEntity;
import com.example.serialization.entities.UserEntity;
import com.example.serialization.entities.dto.UserRegisterDTO;
import com.example.serialization.security.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/")
public class UserController {

    // Kreiranje mini "baze podataka"
    public List<UserEntity> getDummyDB(){
        List<UserEntity> list = new ArrayList<>();

        AddressEntity adr = new AddressEntity();
        adr.setId(1);
        adr.setStreet("Glavna ulica 1");
        adr.setCity("Novi Sad");
        adr.setCountry("Srbija");

        UserEntity ue = new UserEntity();
        ue.setId(1);
        ue.setDateOfBirth(new Date());
        ue.setEmail("user@example.com");
        ue.setName("Vladimir");
        ue.setPassword("password1234");
        ue.setVersion(0);
        ue.setAddress(adr);

        UserEntity ue1 = new UserEntity();
        ue1.setId(2);
        ue1.setDateOfBirth(new Date());
        ue1.setEmail("user2@example.com");
        ue1.setName("Milan");
        ue1.setPassword("password4321");
        ue1.setVersion(0);
        ue1.setAddress(adr);

        adr.getUsers().add(ue);
        adr.getUsers().add(ue1);

        list.add(ue);
        list.add(ue1);

        return list;
    }

    // Vracanje svih korisnika sa njihovim adresama
    /*@RequestMapping(method = RequestMethod.GET)
    public List<UserEntity> getAllUsers(){
        return getDummyDB();
    }

     */

    @RequestMapping(method = RequestMethod.GET, value="/api/v1/users/public")
    @JsonView(Views.Public.class)
    public List<UserEntity> getAllUsers(){
        return getDummyDB();
    }

    @RequestMapping(method = RequestMethod.GET, value="/api/v1/users/private")
    @JsonView(Views.Private.class)
    public List<UserEntity> getAllUsersForPrivate(){
        return getDummyDB();
    }

    @RequestMapping(method = RequestMethod.GET, value="/api/v1/users/admin")
    @JsonView(Views.Admin.class)
    public List<UserEntity> getAllUsersForAdmin(){
        return getDummyDB();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    @JsonView(Views.Admin.class)
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        try{
            List<UserEntity> users = getDummyDB();
            for(UserEntity userEntity:users){
                if(userEntity.getId().equals(id)){
                //ako je korisnik pronadjen vratiti 200
                return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
                }
            }
        // ako korisnik nije pronadjen vratiti 404

            // UNAPREDJENA VERZIJA SA TELOM
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<RESTError>(new RESTError(1, "User not found"),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            // u slucaju izuzetka vratiti 500

            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            // UNAPREDJENA VERZIJA SA TELOM
            return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Rest endpoin za kreiranje Usera unosenjem imena i emaila
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserRegisterDTO user) {
        UserEntity ue = new UserEntity();
        ue.setId(3);
        ue.setEmail(user.getEmail());
        ue.setName(user.getName());
        return new ResponseEntity<UserEntity>(ue, HttpStatus.OK);
    }

}
