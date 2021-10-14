package com.example.oriontek;

import com.example.oriontek.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {

    public final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Optional<Client> getClientById(Long id) {

        var job = repository.findById(id);

        if (job.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return job;
    }

    public List<Client> getClientsByName(String name){

        var clients = repository.findByName(name);

        if (clients.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return clients;
    }

    public List<Client> getAllClients(){

        return repository.findAll();
    }

    public Optional<Boolean> saveClient(Client client){

        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        var clients = repository.findByName(client.getName());

        if (!clients.isEmpty()) {
            client.setId(clients.get(0).getId());

            var clientsAddresses = clients.get(0).getAddress();

            for (var address: client.getAddress()) {
                for (var clientAddress: clientsAddresses) {
                    if (address.getLocation().equals(clientAddress.getLocation())){
                        address.setId(clientAddress.getId());
                    }
                }
            }

            Set<Address> allAddresses = new HashSet<>(clientsAddresses);

            allAddresses.addAll(client.getAddress());

            client.setAddress(allAddresses);
        }

        repository.save(client);

        return Optional.of(true);
    }
}
