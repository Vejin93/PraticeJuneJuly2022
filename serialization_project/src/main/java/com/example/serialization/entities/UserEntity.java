package com.example.serialization.entities;

import com.example.serialization.security.Views;
import com.fasterxml.jackson.annotation.*;

import java.util.Date;

public class UserEntity {

    // Polja

    // Primena anotacije @JsonProperty da bi se u JSON objektima prikazivalo bolje ID umesto id
    // parametar anotacije @JsonProperty("ID") -> "ID" oznacava polje u JSON objektima
    @JsonView(Views.Public.class)
    @JsonProperty("ID")
    private Integer id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Admin.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date dateOfBirth;

    @JsonView(Views.Admin.class)
    private String email;

    // Anotacija za iskljucivanje polja iz serijalizacije - nece se videti prilikom poziva iz POSTMANa
    @JsonIgnore
    private String password;
    private Integer version;

    // Dodata anotacija @JsonManagerReference za vlasnicki kraj veze koji ce biti prikazan i upotpunosti realizovan
    @JsonView(Views.Private.class)
    @JsonManagedReference
    private AddressEntity address;



    // Konstruktor bez parametara
    public UserEntity() {
    }

    // Konstuktor sa parametrima


    public UserEntity(Integer id, String name, Date dateOfBirth, String email, String password, Integer version, AddressEntity address) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.version = version;
        this.address = address;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
