package com.caramello.cafezinho.repository;


import com.caramello.cafezinho.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}

