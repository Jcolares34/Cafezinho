package com.caramello.cafezinho.controller;

import com.caramello.cafezinho.model.Pedido;
import com.caramello.cafezinho.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getAllOrders() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido createOrder(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getOrderById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

