package com.caramello.cafezinho;

import com.caramello.cafezinho.model.Cliente;
import com.caramello.cafezinho.model.Item;
import com.caramello.cafezinho.model.Pedido;
import com.caramello.cafezinho.repository.ClienteRepository;
import com.caramello.cafezinho.repository.ItemRepository;
import com.caramello.cafezinho.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CafezinhoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafezinhoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(PedidoRepository pedidoRepo,
                          ItemRepository itemRepo,
                          ClienteRepository clienteRepo) {
        return args -> {

            // ---------- ITENS (cria ou atualiza) ----------
            Item expresso = itemRepo.findByNome("Expresso").orElse(new Item("Expresso", 35.0));
            expresso.setPreco(35.0);
            itemRepo.save(expresso);

            Item tradicional = itemRepo.findByNome("Tradicional").orElse(new Item("Tradicional", 35.0));
            tradicional.setPreco(35.0);
            itemRepo.save(tradicional);

            Item amarula = itemRepo.findByNome("Amarula").orElse(new Item("Amarula", 35.0));
            amarula.setPreco(35.0);
            itemRepo.save(amarula);

            Item baunilha = itemRepo.findByNome("Baunilha").orElse(new Item("Baunilha", 35.0));
            baunilha.setPreco(35.0);
            itemRepo.save(baunilha);

            Item caramelo = itemRepo.findByNome("Caramelo").orElse(new Item("Caramelo", 35.0));
            caramelo.setPreco(35.0);
            itemRepo.save(caramelo);

            Item menta = itemRepo.findByNome("Menta").orElse(new Item("Menta", 35.0));
            menta.setPreco(35.0);
            itemRepo.save(menta);

            System.out.println("Itens criados ou atualizados!");

            // ---------- CLIENTES (cria ou atualiza) ----------
            Cliente cliente1 = clienteRepo.findByEmail("jco@email.com")
                    .orElse(new Cliente("Julia Colares", "jco@email.com", "123456"));
            cliente1.setSenha("123456");
            clienteRepo.save(cliente1);

            Cliente cliente2 = clienteRepo.findByEmail("admin@email.com")
                    .orElse(new Cliente("Admin", "admin@email.com", "admin"));
            cliente2.setSenha("admin");
            clienteRepo.save(cliente2);

            System.out.println("Clientes criados ou atualizados!");

            // ---------- PEDIDOS (sempre novos) ----------
            Pedido pedidoTeste = new Pedido("Pedido Teste");
            pedidoTeste.setCliente(cliente1);
            pedidoTeste.getItens().add(expresso);
            pedidoRepo.save(pedidoTeste);

            Pedido pedido1 = new Pedido("Pedido 1");
            pedido1.setCliente(cliente1);
            pedido1.getItens().add(expresso);
            pedido1.getItens().add(tradicional);
            pedidoRepo.save(pedido1);

            Pedido pedido2 = new Pedido("Pedido 2");
            pedido2.setCliente(cliente2);
            pedido2.getItens().add(amarula);
            pedido2.getItens().add(baunilha);
            pedidoRepo.save(pedido2);

            System.out.println("Pedidos criados!");
        };
    }

}
