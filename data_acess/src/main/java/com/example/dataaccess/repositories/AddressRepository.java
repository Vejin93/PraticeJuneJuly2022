package com.example.dataaccess.repositories;

import com.example.dataaccess.entities.AddressEntity;
import com.example.dataaccess.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<AddressEntity,Integer> {

    AddressEntity findOneById(Integer id);
    //Optional<AddressEntity> findOne(Integer id);
}
