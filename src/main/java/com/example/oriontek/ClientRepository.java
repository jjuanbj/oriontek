package com.example.oriontek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClientRepository  extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);
}
