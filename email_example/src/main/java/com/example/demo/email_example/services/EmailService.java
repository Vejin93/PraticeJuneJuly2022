package com.example.demo.email_example.services;

import com.example.demo.email_example.models.EmailObject;

public interface EmailService {

    void sendSimpleMessage (EmailObject object);
    void sendTemplateMessage (EmailObject object) throws Exception;
    void sendMessageWithAttachment (EmailObject object, String pathToAttachmet) throws Exception;
}
