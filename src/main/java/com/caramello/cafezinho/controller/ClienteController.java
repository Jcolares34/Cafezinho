package com.caramello.cafezinho.controller;

import com.caramello.cafezinho.model.Cliente;
import com.caramello.cafezinho.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente createClient(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

