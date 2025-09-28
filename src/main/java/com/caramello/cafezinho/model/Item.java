package com.caramello.cafezinho.model;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;    // Nome do café
    private Double preco;   // Preço do café

    // Construtor padrão obrigatório
    public Item() {}

    // Construtor para facilitar testes
    public Item(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}



