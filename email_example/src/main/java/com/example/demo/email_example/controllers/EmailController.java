package com.example.demo.email_example.controllers;

import com.example.demo.email_example.models.EmailObject;
import com.example.demo.email_example.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Controller
@RequestMapping(path="/")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender javaMailSender;

    private static String PATH_TO_ATTACHMENT = "E://proba//slika.jpg";

    @RequestMapping(method = RequestMethod.POST, value="/simpleEmail")
    public ResponseEntity<?> sendSimpleMessage(@RequestBody EmailObject object){
        if(object==null || object.getTo()==null || object.getText()==null) {
            return null;
        }
        emailService.sendSimpleMessage(object);
       return new ResponseEntity<String>("Gotovo!", HttpStatus.OK);
    }

}
