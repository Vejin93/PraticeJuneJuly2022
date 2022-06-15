package com.example.dataaccess.services;

import com.example.dataaccess.entities.AddressEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressDaoImpl implements AddressDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<AddressEntity> findAddressesByUserName(String name){
        String sql = "select a " + "from AddressEntity a " + "left join fetch a.users u " + "where u.name = :name ";
        Query query = em.createQuery(sql);
        query.setParameter("name" , name);

        List<AddressEntity> result = new ArrayList<>();
        result=query.getResultList();
        return result;
    }
}
