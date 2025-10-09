package com.caramello.cafezinho.controller;

import com.caramello.cafezinho.model.Cliente;
import com.caramello.cafezinho.repository.ClienteRepository;
import com.caramello.cafezinho.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private ClienteRepository clienteRepository;

    // Cadastro de usuário
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload) {
        String email = (String) payload.get("email");
        String nomeUsuario = (String) payload.get("nomeUsuario");
        String senha = (String) payload.get("senha");
        String confirmacaoSenha = (String) payload.get("confirmacaoSenha");
        Boolean receberNovidades = payload.get("receberNovidades") != null ? (Boolean) payload.get("receberNovidades") : false;

        if (email == null || email.isBlank() ||
            nomeUsuario == null || nomeUsuario.isBlank() ||
            senha == null || senha.isBlank() ||
            confirmacaoSenha == null || confirmacaoSenha.isBlank()) {
            return ResponseEntity.badRequest().body("Todos os campos (email, nome de usuário, senha, confirmação de senha) são obrigatórios.");
        }
        if (!senha.equals(confirmacaoSenha)) {
            return ResponseEntity.badRequest().body("Senha e confirmação de senha não conferem.");
        }
        Optional<Cliente> existing = clienteRepository.findByEmail(email);
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }
        System.out.println("Payload recebido: " + payload);
        System.out.println("Valor de receberNovidades: " + receberNovidades);
        System.out.println("Tipo de receberNovidades: " + (receberNovidades != null ? receberNovidades.getClass() : "null"));
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setNomeUsuario(nomeUsuario);
        cliente.setSenha(senha);
        cliente.setReceberNovidades(receberNovidades);
        clienteRepository.save(cliente);
        System.out.println("Cliente salvo: " + cliente);
        return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }

    // Login de usuário
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente loginData) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(loginData.getEmail());
        if (cliente.isEmpty() || !cliente.get().getSenha().equals(loginData.getSenha())) {
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
        String token = JwtUtil.generateToken(loginData.getEmail());
        return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
    }
}
