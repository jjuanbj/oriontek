package com.example.oriontek;

import com.example.oriontek.exceptions.RecordNotFoundException;
import com.example.oriontek.repository.AddressRepository;
import com.example.oriontek.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    public final ClientRepository clientRepository;

    public final AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    public Optional<Client> getClientById(Long id) {

        var job = clientRepository.findById(id);

        if (job.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return job;
    }

    public List<Client> getClientsByName(String name){

        var clients = clientRepository.findByName(name);

        if (clients.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return clients;
    }

    public List<Client> getClientsByLocation(String location){

        var clients = clientRepository.findClientsByAddress_Location(location);

        if (clients.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return clients;
    }

    public List<Client> getAllClients(){

        return clientRepository.findAll();
    }

    public Optional<Boolean> saveClient(Client client){

        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        clientRepository.save(client);

        return Optional.of(true);
    }

    public Optional<Boolean> addAddressByClientId(Client client){

        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        var clientSearched = clientRepository.findById(client.getId());

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

        clientRepository.save(client);

        return Optional.of(true);
    }

    public Optional<Boolean> updateClientNameById(Client client){

        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        var clientSearched = clientRepository.findById(client.getId());

        if (clientSearched.isEmpty()) {
            throw new RecordNotFoundException();
        }

        clientSearched.get().setName(client.getName());

        clientRepository.save(clientSearched.get());

        return Optional.of(true);
    }

    public Optional<Boolean> deleteClientById(Long id){

        if (clientRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException();
        }

        clientRepository.deleteById(id);

        return Optional.of(true);
    }

    public Optional<Boolean> deleteClientByName(String name){

        var client = clientRepository.findByName(name);

        if (client.isEmpty()) {
            throw new RecordNotFoundException();
        }

        clientRepository.deleteById(client.get(0).getId());

        return Optional.of(true);
    }
}
