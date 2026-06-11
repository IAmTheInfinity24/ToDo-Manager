# Task Manager

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Build-Maven-blue.svg)](https://maven.apache.org/)
[![Database](https://img.shields.io/badge/Database-H2%20%7C%20MySQL-lightgrey.svg)](#database-profiles)
[![API Docs](https://img.shields.io/badge/API-OpenAPI%20Swagger-success.svg)](#api-documentation)

A Spring Boot task management application with Thymeleaf pages, REST APIs, JWT-based API authentication, validation, global exception handling, and database-backed task persistence.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Database Profiles](#database-profiles)
- [Application URLs](#application-urls)
- [REST API](#rest-api)
- [API Documentation](#api-documentation)
- [Build and Test](#build-and-test)
- [Docker](#docker)
- [CI and Code Quality](#ci-and-code-quality)
- [Troubleshooting](#troubleshooting)

## Overview

Task Manager helps users register, sign in, and manage personal tasks. It includes both server-rendered web pages and JSON endpoints, making it useful as a full-stack Spring MVC sample as well as a REST API learning project.

The default development profile uses an in-memory H2 database, so the project can run locally without installing MySQL. A production profile is included for MySQL deployments.

## Features

- User sign-up and sign-in pages
- Task create, view, update, and delete workflows
- Thymeleaf-based web interface
- REST APIs for task management
- JWT token generation for API authentication
- Spring Security configuration
- Bean validation for users and tasks
- Global exception handling
- Swagger/OpenAPI documentation
- H2 development database profile
- MySQL production database profile
- Maven wrapper support
- Dockerfile and Jenkins pipeline files
- Qodana static-analysis configuration

## Tech Stack

| Area | Technology |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot 3.1.3 |
| Web | Spring MVC, Thymeleaf |
| Security | Spring Security, JWT |
| Persistence | Spring Data JPA, Hibernate |
| Databases | H2 for development, MySQL for production |
| API Docs | Springdoc OpenAPI / Swagger UI |
| Build | Maven |
| Utilities | Lombok, ModelMapper |

## Project Structure

```text
.
|-- src/main/java/org/bdiplus/v1/taskManager
|   |-- config/                 # Spring, security, Swagger, and bean configuration
|   |-- controllers/            # MVC and REST controllers
|   |-- entities/               # JPA entities
|   |-- exceptionHandling/      # Application exceptions and handlers
|   |-- payloads/               # Request/response DTOs
|   |-- repositories/           # Spring Data repositories
|   |-- security/               # JWT authentication classes
|   `-- services/               # Service interfaces and implementations
|-- src/main/resources
|   |-- templates/              # Thymeleaf views
|   |-- application.properties
|   |-- application-dev.properties
|   `-- application-prod.properties
|-- Dockerfile
|-- Jenkinsfile
|-- pom.xml
`-- qodana.yaml
```

## Prerequisites

Install the following before running the project:

- Java JDK 21
- Maven 3.8+ or the included Maven wrapper
- Git
- MySQL 8+ only when running with the `prod` profile
- Postman, curl, or another API client for REST testing

## Quick Start

Clone the repository:

```bash
git clone https://github.com/IAmTheInfinity24/taskManager.git
cd taskManager
```

Run the application with the default development profile:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Open the web application:

```text
http://localhost:8080/
```

## Database Profiles

### Development: H2

The default `application.properties` activates the `dev` profile:

```properties
spring.profiles.active=dev
```

The dev profile uses an in-memory H2 database:

```properties
spring.datasource.url=jdbc:h2:mem:taskmanager_db
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

H2 console:

```text
http://localhost:8080/h2-console
```

Use these connection values in the console:

| Field | Value |
| --- | --- |
| JDBC URL | `jdbc:h2:mem:taskmanager_db` |
| User Name | `sa` |
| Password | leave blank |

### Production: MySQL

The `prod` profile is configured in `src/main/resources/application-prod.properties`.

Before deploying, replace placeholder credentials with environment-specific secrets and avoid committing real passwords:

```properties
spring.datasource.url=jdbc:mysql://<host>:3306/taskmanager_db
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=validate
```

Run with the production profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

Or run the packaged jar:

```bash
java -jar target/taskManager-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## Application URLs

| Page | URL |
| --- | --- |
| Landing page | `http://localhost:8080/` |
| Sign up | `http://localhost:8080/users/sign-up` |
| Sign in | `http://localhost:8080/users/sign-in` |
| H2 console | `http://localhost:8080/h2-console` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |

## REST API

### Authentication

Generate a JWT token:

```http
POST /apis/authenticate
Content-Type: application/json
```

```json
{
  "username": "user@example.com",
  "password": "password123"
}
```

Use the returned token for protected API calls:

```http
Authorization: Bearer <jwt-token>
```

### Task Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/apis/tasks/user/{userId}` | Get all tasks for a user |
| `GET` | `/apis/tasks/{taskId}` | Get one task by ID |
| `POST` | `/apis/tasks/new/{userId}` | Create a task for a user |
| `PUT` | `/apis/tasks/{taskId}` | Update a task |
| `DELETE` | `/apis/tasks/{taskId}` | Delete a task |

Example task payload:

```json
{
  "title": "Prepare release notes",
  "description": "Summarize completed work and deployment steps",
  "dueDate": "2026-06-30",
  "completed": false
}
```

## API Documentation

After starting the application, visit:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger UI provides an interactive view of the available REST endpoints and request/response schemas.

## Build and Test

Compile and run tests:

```bash
./mvnw clean test
```

Build the executable jar:

```bash
./mvnw clean package
```

Run the jar:

```bash
java -jar target/taskManager-0.0.1-SNAPSHOT.jar
```

## Docker

A `Dockerfile` is included for container builds:

```bash
docker build -t task-manager .
docker run --rm -p 8080:8080 task-manager
```

Note: the Maven build currently targets Java 21. Keep the Docker base images aligned with the Java version in `pom.xml` before relying on container builds.

## CI and Code Quality

- `Jenkinsfile` defines a Jenkins pipeline for build automation.
- `qodana.yaml` contains Qodana configuration for static analysis.
- Maven wrapper files are included so CI agents do not need a global Maven installation.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `Unsupported class file major version` | Use JDK 21 for local builds and runtime. |
| Swagger URL returns 404 | Use `/swagger-ui/index.html` with Springdoc OpenAPI 2.x. |
| H2 console login fails | Confirm the JDBC URL is `jdbc:h2:mem:taskmanager_db`, username is `sa`, and password is blank. |
| MySQL connection fails in `prod` | Verify host, port, database name, credentials, firewall rules, and that MySQL is reachable. |
| Lombok-generated methods are missing in IDE | Enable annotation processing and install the Lombok plugin if your IDE requires it. |

## License

No license file is currently included. Add one before distributing or reusing this project publicly.
