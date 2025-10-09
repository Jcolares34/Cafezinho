package com.caramello.cafezinho.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Removido campo nome
    @Column(unique = true)
    private String email;
    private String senha;
    private String nomeUsuario;
    private Boolean receberNovidades = false;

    public Cliente() {}

    public Cliente(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public Boolean getReceberNovidades() { return receberNovidades; }
    public void setReceberNovidades(Boolean receberNovidades) { this.receberNovidades = receberNovidades; }

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Pedido> pedidos = new HashSet<>();

}
