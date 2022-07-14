package com.example.security.controllers;

import com.example.security.entities.RoleEntity;
import com.example.security.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    public List<UserEntity> getDummyDB(){

        List<UserEntity> list = new ArrayList<>();

        RoleEntity re = new RoleEntity();
        re.setId(1);
        re.setName("admin");

        UserEntity ue = new UserEntity();
        ue.setId(1);
        ue.setEmail("user@example.com");
        ue.setName("Vladimir");
        ue.setLastName("Vejin");
        ue.setPassword("password1234");
        ue.setRole(re);

        UserEntity ue1 = new UserEntity();
        ue1.setId(2);
        ue1.setEmail("user2@example.com");
        ue1.setName("Ratko");
        ue1.setLastName("Salak");
        ue1.setPassword("password4321");
        ue1.setRole(re);

        list.add(ue);
        list.add(ue1);

        return list;
    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(){
        return new ResponseEntity<List<UserEntity>>(getDummyDB(), HttpStatus.OK);
    }
}

