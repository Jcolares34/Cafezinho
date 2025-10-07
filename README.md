# Cafezinho Backend

Backend desenvolvido em Spring Boot para gerenciar itens, clientes e pedidos de um sistema de cafeteria.

## Tecnologias
- Java 21
- Spring Boot 3.5.6
- PostgreSQL
- Maven

## Como rodar o projeto
1. Configure o banco de dados PostgreSQL conforme o arquivo `src/main/resources/application.properties`.
2. Certifique-se de que o JAVA_HOME está configurado para o JDK 21.
3. Execute o comando:
   ```cmd
   .\mvnw.cmd spring-boot:run
   ```
4. O backend estará disponível em `http://localhost:8080`.

## Endpoints REST

### Itens
- **GET /api/items**: Retorna todos os itens.
- **POST /api/items**: Adiciona um novo item.
  - Body (JSON):
    ```json
    {
      "nome": "Café",
      "preco": 5.0
    }
    ```
- **GET /api/items/{id}**: Retorna um item específico.

### Clientes
- **GET /api/clients**: Retorna todos os clientes.
- **POST /api/clients**: Adiciona um novo cliente.
  - Body (JSON):
    ```json
    {
      "nome": "João",
      "email": "joao@email.com"
    }
    ```
- **GET /api/clients/{id}**: Retorna um cliente específico.

### Pedidos
- **GET /api/orders**: Retorna todos os pedidos.
- **POST /api/orders**: Cria um novo pedido.
  - Body (JSON):
    ```json
    {
      "clienteId": 1,
      "itens": [1, 2]
    }
    ```
- **GET /api/orders/{id}**: Retorna um pedido específico.

## Testando os endpoints
Você pode testar os endpoints usando Postman, cURL ou Insomnia. Exemplos de comandos cURL:

- Listar todos os itens:
  ```cmd
  curl -X GET http://localhost:8080/api/items
  ```
- Adicionar um item:
  ```cmd
  curl -X POST http://localhost:8080/api/items -H "Content-Type: application/json" -d "{\"nome\":\"Café\",\"preco\":5.0}"
  ```

## Estrutura do projeto
```
Cafezinho/
├── src/
│   ├── main/
│   │   ├── java/com/caramello/cafezinho/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── pom.xml
└── README.md
```

## Observações
- Certifique-se de que o banco PostgreSQL está rodando e acessível.
- As tabelas são criadas automaticamente pelo Hibernate.
- Para dúvidas ou sugestões, abra uma issue no repositório.

