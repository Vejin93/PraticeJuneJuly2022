package com.example.dataaccess.controllers;

import com.example.dataaccess.entities.AddressEntity;
import com.example.dataaccess.entities.UserEntity;
import com.example.dataaccess.repositories.AddressRepository;
import com.example.dataaccess.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/users")
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    //1. Zadatak - Dodavanje novog korisnika u bazu podataka
    @RequestMapping(method = RequestMethod.POST)
    public UserEntity addNewUser(@RequestParam String name, @RequestParam String email){
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    //2. Zadatak - Izlistavanje svih korisnika iz baze podataka
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    //3. Zadatak - Trazenje korisnika po Id-ju
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public Optional<UserEntity> findUserById(@PathVariable Integer id){
        return userRepository.findById(id);
    }

    //4. Zadatak - Trazenje korisnika po Id-ju i menjanje njegovog imena i maila
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    /* PRE DODAVANJA POLJA JMBG, numberOfId, dateOfBirth, phoneNumber
    public UserEntity updateUserById(@PathVariable Integer id,@RequestParam String name, @RequestParam String email){
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return user;
        */
    public UserEntity updateUserById(@PathVariable Integer id, @RequestParam String name, @RequestParam String email,
                                     @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfBirth, @RequestParam String phoneNumber, @RequestParam String JMBG,
                                     @RequestParam String numberOfId){
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        user.setPhoneNumber(phoneNumber);
        user.setJMBG(JMBG);
        user.setNumberOfId(numberOfId);
        userRepository.save(user);
        return user;
    }

    //5. Zadatak - Brisanje korisnika iz baze podataka
    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void deleteById(@PathVariable Integer id){
        userRepository.deleteById(id);
    }

    //6. Zadatak - pronalazenje korisnika po email adresi
    @RequestMapping(method = RequestMethod.GET, value="/by-email")
    public List<UserEntity> findByEmail(@RequestParam String email){
        return userRepository.findByEmail(email);
    }

    //7. Zatadak - pronalazenje korisnika po imenu i sortiranje korisnika po rastucoj vrednosti imena
    @RequestMapping(method = RequestMethod.GET, value="/by-name")
    public List<UserEntity> findByNameOrderByNameAsc(@RequestParam String name){
        return userRepository.findByNameStartsWithOrderByNameAsc(name);
    }

    //8. Zadatak - pronalazenje korisnika po datumu rodjenja sortiranih u rastucem redosledu imena
    @RequestMapping(method = RequestMethod.GET, value="/by-dob")
    public List<UserEntity> findBydateOfBirth(@DateTimeFormat(pattern="dd-MM-yyyy") Date dateOfBirth){
        return userRepository.findBydateOfBirthOrderByNameAsc(dateOfBirth);
    }

    //9. Zadatak - pronalazenje korisnika cije ime pocinje na prosledjeno slovo
    //!!! RADI SAMO PRVI PUT, POSLE MI IZBACUJE GRESKU 500 INTERNAL SERVER ERROR
    @RequestMapping(method = RequestMethod.GET, value="/by-name-first-letter")
    public List<UserEntity> findByNameStartingWith(@RequestParam String name){
        return userRepository.findByNameStartingWith(name);
    }

    // 10. Zadatak - koji postavlja adresu prebivalista za odredjenog klijenta

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}/addresses")
    public UserEntity addAddressToUser(@PathVariable Integer id, @RequestParam Integer address){
            UserEntity user = userRepository.findOneById(id);
            AddressEntity adr = addressRepository.findOneById(address);
            user.setAddress(adr);
            userRepository.save(user);
            return user;
    }

}
