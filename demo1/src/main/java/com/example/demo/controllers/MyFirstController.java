package com.example.demo.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
public class MyFirstController {

    @RequestMapping("/")
    public String hello(){
        return "Moja prva aplikacija";
    }

    @RequestMapping("/greetings")
    public String helloName(){
        return "Hello Vladimir!";
    }

    @RequestMapping("/date")
    public String currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @RequestMapping("/family")
    public List membersOfFamily(){
        List members = new LinkedList();
        members.add("Srecko");
        members.add("Gordana");
        members.add("Sladjana");
        members.add("Vladimir");
        return members;
    }

    @RequestMapping("/array")
    public int[] niz(){
        int[] niz={23,41,32,44,55};
        return niz;
    }

    @RequestMapping("/sortarray")
    public int[] sortiraniNiz(){
        int[] niz={23,41,32,44,55};
        Arrays.sort(niz);
        return niz;
    }

    @RequestMapping("/minmax")
    public String minmax(){
        int[] niz={23,41,32,44,55};
        Arrays.sort(niz);
        return "Najmanji clan niza je: " + niz[0]+", dok je najveci clan niza: " + niz[niz.length-1];
    }



}
