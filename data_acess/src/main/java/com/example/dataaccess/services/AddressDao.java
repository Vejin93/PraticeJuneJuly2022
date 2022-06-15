package com.example.dataaccess.services;

import com.example.dataaccess.entities.AddressEntity;

import java.util.List;

public interface AddressDao {

    public List<AddressEntity> findAddressesByUserName(String name);
}
