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

---

