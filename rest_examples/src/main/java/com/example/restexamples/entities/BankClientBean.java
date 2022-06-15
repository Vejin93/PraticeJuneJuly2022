package com.example.restexamples.entities;


import java.time.LocalDate;
import java.util.Date;

public class BankClientBean {

    protected Integer id;
    protected String name;
    protected String surname;
    protected String email;

    protected LocalDate dateOfBirth;

    protected String bonitet;

    protected String city;

    public BankClientBean() {
    }

    public BankClientBean(Integer id, String name, String surname, String email, LocalDate dateOfBirth, String bonitet, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.bonitet = bonitet;
        this.city = city;
    }

    // Konstruktor pre dodavanja datuma rodjenja, boniteta i grada
    public BankClientBean(Integer id, String name, String surname, String email, LocalDate dateOfBirth, String bonitet) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.bonitet = bonitet;
    }

    // Konstruktor pre dodavanja datuma rodjenja i boniteta
    public BankClientBean(Integer id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBonitet() {
        return bonitet;
    }

    public void setBonitet(String bonitet) {
        this.bonitet = bonitet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
