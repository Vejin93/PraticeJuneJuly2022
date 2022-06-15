package com.example.dataaccess.controllers;

import com.example.dataaccess.entities.AddressEntity;
import com.example.dataaccess.repositories.AddressRepository;
import com.example.dataaccess.services.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressDao addressDao;

    @RequestMapping(method = RequestMethod.POST)
    public AddressEntity addNewAddress(@RequestParam String street, @RequestParam String city, @RequestParam String country){
        AddressEntity address = new AddressEntity();
        address.setStreet(street);
        address.setCity(city);
        address.setCountry(country);
        addressRepository.save(address);
        return address;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AddressEntity> getAllAddress(){
        return addressRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/user/{name}")
    public List<AddressEntity> getAddressForAUser(@PathVariable String name){
        return addressDao.findAddressesByUserName(name);
    }
}
