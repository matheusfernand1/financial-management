# Financial Management

Aplicação para gerenciamento financeiro pessoal.

## Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/matheus-fernand/financial-management.git
   ```

2. Execute o build do projeto:
   ```bash
   ./gradlew build
   ```

3. Inicie a aplicação:
   ```bash
   ./gradlew bootRun
   ```

4. Acesse no navegador:
   ```
   http://localhost:8080
   ```

5. Para testar a API, você pode usar o Postman ou qualquer outro cliente HTTP. Abaixo estão alguns exemplos de requisições:

- **Criar Conta**:
  ```http
  POST /conta

   {
     "numero_conta": 234,
     "saldo": 180.37
   }
  ```

- **Listar Contas**:
  ```http
  GET /conta?numero_conta=234
    ```
- **Criar Transação**:
  ```http
  POST /transacao

  {
    "valor": "100",
    "numero_conta": "234",
    "forma_pagamento": "D"
  }

## Tecnologias Utilizadas
- **Java 17**: Linguagem de programação.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para acesso a dados e mapeamento objeto-relacional.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.

## Arquitetura

A aplicação segue uma arquitetura em camadas (Layered architecture), separando responsabilidades em:

- **Controller**: Responsável por receber e responder às requisições HTTP.
- **Service**: Contém a lógica de negócio da aplicação.
- **Repository**: Responsável pela comunicação com o banco de dados.
- **Model/Entity**: Representa as entidades do domínio.

## Principais Design Patterns Utilizados

- **Singleton**: O Spring gerencia os beans de serviço como singletons por padrão, como as classes em `service` (`ContaService`, `TransacaoService`). Assim, existe apenas uma instância dessas classes durante o ciclo de vida da aplicação.
- **Repository**: Abstrai o acesso a dados, facilitando testes e manutenção.
- **Service Layer**: Centraliza a lógica de negócio, promovendo reutilização e organização do código.