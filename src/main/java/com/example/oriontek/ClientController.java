package com.example.oriontek;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable(name = "id") Long id) {

        var test = service.getClientById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(test);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getClientsByName(@PathVariable(name = "name") String name) {

        return ResponseEntity.status(HttpStatus.OK).body(service.getClientsByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClients() {

        return ResponseEntity.status(HttpStatus.OK).body(service.getAllClients());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveClient(@Valid @RequestBody Client client) {

        return ResponseEntity.status(HttpStatus.OK).body(service.saveClient(client));
    }
}
