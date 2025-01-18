---

# 📚 Sistema de Gerenciamento de Biblioteca

Este projeto consiste no desenvolvimento de um sistema completo para gerenciar uma biblioteca. O sistema permite que usuários realizem empréstimos de livros, acompanhem informações sobre os empréstimos e integrem operações com serviços modernos, como RabbitMQ e MongoDB.

---

## ✨ Funcionalidades

- **Registro de Empréstimos:** Consumo de mensagens de uma fila RabbitMQ para registrar novos empréstimos.
- **Armazenamento Eficiente:** Gerenciamento de dados de usuários, livros e empréstimos em um banco de dados MongoDB.
- **API REST:** Endpoints dedicados para consultar informações sobre empréstimos e usuários.
- **Agregações:** Geração de relatórios, como quantidade de livros emprestados por usuário e listagem de empréstimos ativos.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot**  
  - Spring Web  
  - Spring Data MongoDB  
  - Spring AMQP (RabbitMQ)  
- **MongoDB**  
- **RabbitMQ**  
- **Docker** (para contêinerização de MongoDB e RabbitMQ)

---

## 📋 Etapas do Projeto  

### 1. Configuração Inicial do Projeto  

#### Criar um novo projeto no **Spring Initializr**  

1. Acesse o [Spring Initializr](https://start.spring.io/).  
2. Configure o projeto com as seguintes opções:  
   - **Linguagem:** Java  
   - **Ferramenta de Build:** Maven  
   - **Versão do Spring Boot:** 3.4.1  
   - **Grupo:** `com.hildo.costa`  
   - **Artefato:** `library`  
   - **Nome do Projeto:** `library`  
   - **Descrição:** `Library Management`  
   - **Tipo de Empacotamento:** `JAR`  
   - **Versão do Java:** 17  
3. Adicione as dependências:  
   - **Spring Web**  
   - **Spring Data MongoDB**  
   - **Spring for RabbitMQ**  
4. Clique em **Generate** para baixar o projeto.

### 2. Estrutura do Projeto  

Organize o projeto no seguinte formato:  

```plaintext
src/main/java  
└── com  
    └── hildo  
        └── costa  
            └── library
                ├── LibraryApplication.java    # Classe principal para execução do Spring Boot  
                ├── config                     # Configurações do RabbitMQ e MongoDB  
                ├── controller                 # Controladores para os endpoints  
                ├── dto                        # Data Transfer Objects  
                ├── model                      # Classes de modelo (Usuario, Livro, Emprestimo)  
                ├── repository                 # Interfaces de repositórios MongoDB  
                └── service                    # Serviços com lógica de negócio  
```

---

### 3. Configuração do MongoDB e RabbitMQ  

#### Configurar o arquivo `application.properties`  

No arquivo `src/main/resources/application.properties`, adicione as configurações abaixo:  

```properties
# Nome da aplicação
spring.application.name=librarymanagement

# Configuração do MongoDB
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=librarymanagement
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.username=admin
spring.data.mongodb.password=123
spring.data.mongodb.auto-index-creation=true

# Configuração do RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Configurações adicionais de logs para depuração
logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG
logging.level.org.springframework.amqp.rabbit.connection=DEBUG
```

---

### 4. Desenvolvimento de Funcionalidades  

#### **Registro de Empréstimos**  
1. Configure o RabbitMQ para consumir mensagens da fila.  
2. Salve as informações no MongoDB utilizando as coleções:  
   - **Usuários**  
   - **Livros**  
   - **Empréstimos**  

#### **Listagem de Livros Emprestados por Usuário**  
- **Endpoint:** `GET /usuarios/{idUsuario}/emprestimos`  
  - Retorna a lista de livros emprestados por um usuário.  

#### **Quantidade Total de Livros Emprestados**  
- **Endpoint:** `GET /emprestimos/total`  
  - Retorna o número total de livros emprestados no sistema.  

#### **Detalhes de um Empréstimo**  
- **Endpoint:** `GET /emprestimos/{idEmprestimo}`  
  - Retorna informações detalhadas de um empréstimo específico.  

---

### 5. Exemplo de Mensagem RabbitMQ  

Uma mensagem consumida do RabbitMQ deve seguir o formato:  

```json
{
  "idUsuario": 1,
  "livros": [
    {
      "titulo": "Java para Iniciantes",
      "autor": "John Doe",
      "quantidade": 1
    },
    {
      "titulo": "Spring Boot Avançado",
      "autor": "Jane Doe",
      "quantidade": 1
    }
  ],
  "dataEmprestimo": "2025-01-17",
  "dataDevolucao": "2025-02-01"
}
```

---

### 6. Utilização de Docker  

Para executar os serviços necessários, como MongoDB e RabbitMQ, configure um arquivo `docker-compose.yml` no diretório do projeto:  

```yaml
version: '3.8'
services:
  mongodb:
    image: mongo:5.0
    container_name: mongodb
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: 123
  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
```

---

## 🚀 Como Executar  

1. Suba os serviços do MongoDB e RabbitMQ utilizando o Docker:  
   ```bash
   docker-compose up -d
   ```  

2. Execute o projeto Spring Boot:  
   ```bash
   mvn spring-boot:run
   ```  

3. Acesse os endpoints da API utilizando uma ferramenta como Postman ou cURL.


Passo 2: Criar as Entidades e o Modelo de Dados
Objetivo
Mapear as entidades principais do sistema no código. Isso inclui a criação das classes que representam os dados armazenados no MongoDB e que serão consumidos do RabbitMQ.

2. Criar as Entidades
a. Entidade Book
A entidade Book representa os livros na biblioteca. Crie o arquivo Book.java no pacote model com o seguinte conteúdo:

package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books") // Define a coleção no MongoDB
public class Book {

    @Id
    private String id;          // Identificador único
    private String title;       // Título do livro
    private String author;      // Autor do livro
    private String genre;       // Gênero literário
    private double price;       // Preço do livro

    // Construtor padrão
    public Book() {}

    // Construtor completo
    public Book(String id, String title, String author, String genre, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

b. Entidade Loan
A entidade Loan representa os empréstimos de livros. Crie o arquivo Loan.java no pacote model com o seguinte conteúdo:

package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "loans") // Define a coleção no MongoDB
public class Loan {

    @Id
    private String id;          // Identificador único
    private String bookId;      // ID do livro emprestado
    private String borrower;    // Nome do usuário que pegou o livro
    private LocalDate loanDate; // Data do empréstimo
    private LocalDate dueDate;  // Data de devolução prevista

    // Construtor padrão
    public Loan() {}

    // Construtor completo
    public Loan(String id, String bookId, String borrower, LocalDate loanDate, LocalDate dueDate) {
        this.id = id;
        this.bookId = bookId;
        this.borrower = borrower;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

4. Criar os Repositórios
a. Repositório BookRepository
No pacote repository, crie a interface BookRepository.java para manipular dados de livros:


package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}


b. Repositório LoanRepository
No mesmo pacote, crie a interface LoanRepository.java para manipular dados de empréstimos:

package com.example.library.repository;

import com.example.library.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<Loan, String> {
}


---

