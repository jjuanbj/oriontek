package com.example.oriontek;

import com.example.oriontek.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

        repository.save(client);

        return Optional.of(true);
    }

    public Optional<Boolean> addAddressByClientId(Client client){

        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        var clientSearched = repository.findById(client.getId());

        if (clientSearched.isEmpty()) {
            throw new RecordNotFoundException();
        }

        var oldClientsAddresses = clientSearched.get().getAddress();

        for (var newAddress: client.getAddress()) {
            for (var clientAddress: oldClientsAddresses) {
                if (newAddress.getLocation().equals(clientAddress.getLocation())){
                    throw new IllegalArgumentException("Cannot add address with same previous location");
                }
            }

            oldClientsAddresses.add(newAddress);
        }

        client.setName(clientSearched.get().getName());
        client.setAddress(oldClientsAddresses);

        repository.save(client);

        return Optional.of(true);
    }
}
