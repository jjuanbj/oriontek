package com.example.oriontek;

import com.example.oriontek.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OriontekApplication {

	public static void main(String[] args) {
		SpringApplication.run(OriontekApplication.class, args);
	}

	@Bean
	public String initializeProgram(ClientRepository repository) {

		var client = new Client();
		client.setName("Juan");

		var address = new Address();
		address.setLocation("Libertador de Herrera");

		Set<Address> addresses = new HashSet<>();
		addresses.add(address);

		client.setAddress(addresses);

		repository.save(client);

		return "success";
	}
}
