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

### Build Project
```bash
./gradlew build
```