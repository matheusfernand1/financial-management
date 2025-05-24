# Financial Management

Aplicação para gerenciamento financeiro.

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

## Exemplos de aplicação dos principíos SOLID
- **Single Responsibility Principle (SRP)**: Cada classe tem uma única responsabilidade. Por exemplo, `ContaService` é responsável apenas por operações relacionadas a contas.
- **Open/Closed Principle (OCP)**: As classes estão abertas para extensão, mas fechadas para modificação. Por exemplo, se quisermos adicionar um novo tipo de transação, podemos criar uma nova classe que estenda a funcionalidade existente sem modificar as classes já existentes.
- **Liskov Substitution Principle (LSP)**: As subclasses podem ser usadas no lugar das superclasses sem alterar o comportamento esperado. Por exemplo, se tivermos uma classe `ContaPremium` que estende `Conta`, podemos usá-la onde uma `Conta` é esperada.
- **Interface Segregation Principle (ISP)**: As interfaces são específicas e não forçam implementações desnecessárias. Por exemplo, se tivermos uma interface `Transacao`, ela pode ter métodos específicos para diferentes tipos de transações, evitando que uma classe implemente métodos que não utiliza.
- **Dependency Inversion Principle (DIP)**: As classes de alto nível não dependem de classes de baixo nível, ambas dependem de abstrações. Por exemplo, `ContaService` depende da interface `ContaRepository`, permitindo que possamos trocar a implementação do repositório sem afetar o serviço.
