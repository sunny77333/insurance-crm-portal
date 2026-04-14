package com.insurance.crm.controller;

import com.insurance.crm.model.Adviser;
import com.insurance.crm.model.Client;
import com.insurance.crm.model.User;
import com.insurance.crm.repository.AdviserRepository;
import com.insurance.crm.repository.ClientRepository;
import com.insurance.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adviser")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdviserController {

    private final AdviserRepository adviserRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    // Get adviser profile
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        Adviser adviser = adviserRepository.findByUser(user)
                .orElse(null);
        if (adviser == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(adviser);
    }

    // Get all clients for this adviser
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        Adviser adviser = adviserRepository.findByUser(user)
                .orElse(null);
        if (adviser == null) return ResponseEntity.notFound().build();

        List<Client> clients = clientRepository.findByAdviser(adviser);
        return ResponseEntity.ok(clients);
    }

    // Get a specific client by ID
    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id,
                                       Authentication authentication) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(client);
    }

    // Add a new client
    @PostMapping("/clients")
    public ResponseEntity<?> addClient(@RequestBody Client client,
                                       Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        Adviser adviser = adviserRepository.findByUser(user)
                .orElse(null);
        if (adviser == null) return ResponseEntity.notFound().build();

        client.setAdviser(adviser);
        clientRepository.save(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    // Update a client
    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id,
                                          @RequestBody Client updatedClient) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return ResponseEntity.notFound().build();

        client.setPhone(updatedClient.getPhone());
        client.setAddress(updatedClient.getAddress());
        client.setDateOfBirth(updatedClient.getDateOfBirth());
        client.setPolicyNumber(updatedClient.getPolicyNumber());
        client.setPolicyType(updatedClient.getPolicyType());
        clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    // Delete a client
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Client deleted successfully");
    }
}