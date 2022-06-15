package com.example.dataaccess.repositories;

import com.example.dataaccess.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Integer>{

    // Metod za trazenje korisnika po emailu
    List<UserEntity> findByEmail(String email);

    // Metod za trazenje korsinika po imenu i sortirati ih po imenu rastucim redosledom
    List<UserEntity> findByNameStartsWithOrderByNameAsc(String name);

    // Metod za trazenje korisnika po datumu rodjenja i sortiranje po imenu u rastucem redosledu
    List<UserEntity> findBydateOfBirthOrderByNameAsc(Date dateOfBirth);

    // Metod za pronalazenje razlicitih imena korisnika po prvom slovu imena
    List<UserEntity> findByNameStartingWith(String name);

    //Pronalazenje jednog korisnika
    //Optional<UserEntity> findOne(Integer id);
    UserEntity findOneById(Integer id);
}
