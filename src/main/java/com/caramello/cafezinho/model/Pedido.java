package com.caramello.cafezinho.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    // Relacionamento com Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento com Itens (vários cafés por pedido)
    @ManyToMany
    @JoinTable(
            name = "pedido_item",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> itens = new HashSet<>();

    // -------------------- Construtores --------------------
    // Construtor padrão (necessário pro Hibernate)
    public Pedido() {}

    // Construtor com descrição (facilita criar pedidos no CommandLineRunner)
    public Pedido(String descricao) {
        this.descricao = descricao;
    }

    // -------------------- Getters e Setters --------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Set<Item> getItens() { return itens; }
    public void setItens(Set<Item> itens) { this.itens = itens; }
}
