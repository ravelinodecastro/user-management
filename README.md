# User Management REST API

This project is a **RESTful web service** built with **Spring Boot**, using **Spring Data JPA** for persistence and **H2 in-memory database**.  
It provides full CRUD operations for managing users and follows a layered architecture with DTOs, validation, and global exception handling.

---

## Technologies Used

- Java 25
- Spring Boot 4
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation (Jakarta Validation)
- Lombok
- Springdoc OpenAPI (Swagger)
- Gradle (via gradlew)
- Docker
- Docker Compose

---

## Features

- Create a user
- Retrieve all users
- Retrieve user by ID
- Update user
- Delete user
- Email format validation
- Unique email constraint
- Structured error handling
- Swagger UI documentation

---

## Project Structure
```text
pt.uevora.usermanagement
│
├── controllers
├── services
├── repositories
├── entities
├── dto
├── mappers
└── exceptions
│
├── Dockerfile
├── docker-compose.yml
```

---

## User Entity

A user contains the following attributes:

- `id` (auto-generated)
- `name`
- `email` (must be unique and valid)

---

## API Endpoints

### Create User
POST /users
### Get All Users
GET /users


### Get User by ID

GET /users/{id}


### Update User

PUT /users/{id}


### Delete User

DELETE /users/{id}
## Example Request

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
 ```
## Example Response

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com"
}
 ```
## Error Handling
The API uses a structured error response format.
### Validation Error Example
```json
{
  "status": 400,
  "error": "Validation Failed",
  "timestamp": "2026-04-25T10:00:00Z",
  "path": "/users",
  "fieldErrors": [
    {
      "field": "email",
      "message": "O email deve ser válido"
    }
  ]
}
 ```
### Email Conflict
```json
{
    "status": 409,
    "error": "Conflict",
    "timestamp": "2026-04-25T10:00:00Z",
    "path": "/users",
    "fieldErrors": [
        {
            "field": "email",
            "message": "Este e-mail já encontra-se registado: user@example.com"
        }
    ]
}
```

### User Not Found
```json
{
  "status": 404,
  "error": "Não existe algum utilizador com id: 9",
  "timestamp": "2026-04-25T10:00:00Z",
  "path": "/users/{id}",
  "fieldErrors": []
}
 ```
## Swagger UI

After running the application, access the API documentation:

- Swagger UI:  
  http://localhost:8080/swagger-ui/index.html

- OpenAPI specification:  
  http://localhost:8080/v3/api-docs
## Database Configuration
The project uses H2 in-memory database.
### Console Access
http://localhost:8080/h2-console
## JDBC URL
jdbc:h2:mem:testdb
## Running the Project

### Using Gradle Wrapper
```bash
./gradlew bootRun
```

### Running with Docker Compose
```bash
docker-compose up --build
```

### Build Project
```bash
./gradlew build
```


## CI/CD Pipeline (GitHub Actions)

Este projeto utiliza **GitHub Actions** para implementar um pipeline de **CI/CD (Continuous Integration e Continuous Delivery)** que automatiza o build da aplicação e a publicação de uma imagem Docker no Docker Hub.

O pipeline é executado automaticamente sempre que é criado um *tag* no repositório com o formato:

```
v*.*.*
```

Exemplo:

```
v1.0.0
v1.1.0
```

---

## Pipeline Overview

O workflow encontra-se definido em:

```
.github/workflows/docker-publish.yml
```

### Etapas do pipeline

Sempre que um novo tag é criado, o pipeline executa automaticamente:

1. **Checkout do código**

   * Clona o repositório para o runner do GitHub Actions.

2. **Configuração do Docker Buildx**

   * Permite builds multi-platform e mais eficientes.

3. **Login no Docker Hub**

   * Autenticação usando secrets do GitHub:

     * `DOCKERHUB_USERNAME`
     * `DOCKERHUB_TOKEN`

4. **Extração da versão**

   * A versão é automaticamente derivada do tag (`v1.0.0 → 1.0.0`).

5. **Build e push da imagem Docker**

   * A imagem é construída e publicada no Docker Hub com duas tags:

     * versão específica (`1.0.0`)
     * `latest`

---

## Docker Image Repository

A imagem Docker é publicada no Docker Hub:

```
ravelinodecastro/user-management
```

Tags publicadas:

* `latest`
* `X.Y.Z` (baseado no Git tag)

---

## Configuração (Parametrização)

O pipeline foi desenhado para ser facilmente configurável através de **GitHub Secrets**:

### Secrets necessários

| Secret               | Descrição                     |
| -------------------- | ----------------------------- |
| `DOCKERHUB_USERNAME` | Username da conta Docker Hub  |
| `DOCKERHUB_TOKEN`    | Token de acesso do Docker Hub |

---

## Decisões de Implementação

* **Trigger por tags (`v*.*.*`)**

  * Garante versionamento explícito e controlado.
* **Uso de Docker Hub**

  * Repositório público conforme requisito da avaliação.
* **Dual tagging (`latest` + version)**

  * Permite tanto deploy estável como rastreabilidade de versões.
* **Build automatizado no CI**

  * Evita builds manuais e garante consistência entre versões.
* **Secrets no GitHub**

  * Evita exposição de credenciais no repositório.

---

## Como usar o pipeline

Para publicar uma nova versão:

```bash
git tag v1.0.0
git push origin v1.0.0
```

O GitHub Actions irá automaticamente:

* compilar o projeto
* construir a imagem Docker
* publicar no Docker Hub
