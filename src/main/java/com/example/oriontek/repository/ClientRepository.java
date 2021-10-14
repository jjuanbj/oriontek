package com.example.oriontek.repository;

import com.example.oriontek.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClientRepository  extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);

    @Query(value = "select c from Client c inner join c.address a where a.location = :location")
    List<Client> findClientsByAddress_Location(String location);
}
