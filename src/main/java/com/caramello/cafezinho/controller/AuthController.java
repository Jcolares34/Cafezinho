package com.caramello.cafezinho.controller;

import com.caramello.cafezinho.model.Cliente;
import com.caramello.cafezinho.repository.ClienteRepository;
import com.caramello.cafezinho.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

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
        System.out.println("Tipo de receberNovidades: " + receberNovidades.getClass().getName());
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
        System.out.println("Login attempt recebido para o email: " + loginData.getEmail());
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(loginData.getEmail());
        if (clienteOpt.isEmpty() || !clienteOpt.get().getSenha().equals(loginData.getSenha())) {
            return ResponseEntity.status(401).body(Map.of("error", "Email ou senha inválidos."));
        }
        Cliente cliente = clienteOpt.get();
        String token = JwtUtil.generateToken(cliente.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", cliente.getEmail());
        response.put("nomeUsuario", cliente.getNomeUsuario());

        // Registra o mapa de resposta para que possamos confirmar que o backend retorna os três campos
        System.out.println("Resposta do login: " + response);

        return ResponseEntity.ok(response);
    }
}
