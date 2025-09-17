<p align="center">
  <img src="https://i.ibb.co/DfB4zkTp/Yo-La-Tengo800x800-nobackground.png" alt="YaLoTengo Logo" width="300" />
</p>


**YaLoTengo** is a collaborative economy platform that facilitates the lending and borrowing of occasional-use items between users, aiming to promote conscious consumption and reduce unnecessary purchases.

The backend is being developed using **Java 21**, **Spring Boot 3.5.3** and **Spring Security**.

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Maven**
- **MySQL 8.0**
- **Lombok**
- **JUnit & Mockito (5.2.0)**
- **Postman (API testing)**

---

## 🚧 Core Features in Development (MVP)

- 📦 **Domain modeling** of `User` and `Item` entities with JPA.
- 🔗 **One-to-Many relationship**: one user can register multiple items.
- 🔐 **User authentication and authorization**:
    - Secure registration and login
    - JWT-based stateless authentication
    - Role-based access control
- 📤 **Item sharing functionality**:
    - Authenticated users can publish items available for lending
    - Items are linked to their owner (user)
- 🧱 **CRUD operations**:
    - Full RESTful endpoints for users and items
    - Input validation with meaningful HTTP responses
- ✅ **Testing**:
    - Unit and integration tests using JUnit and Mockito
    - Minimum of 70% code coverage
- 🐳 **Containerization**:
    - Docker support for local development and deployment
- 📁 **Image management**:
    - Automated image upload and storage integration (planned)

---

## 💡 Vision

Our goal is to build a secure, efficient, and easy-to-maintain platform that encourages responsible sharing through a point-based system, fostering community and sustainability.

---

## 🐳 Run the API with Docker
This guide shows how to run the Spring Boot API using Docker and Docker Compose. It includes example config files you can copy–paste.

### - **Quick Start**: Make sure you have installed Docker.

## 1. Clone the repository

   git clone https://github.com/yalotengoapp/backend.git

   cd backend


## 2. Configure environment variables

#### At the project root, create a file named .env with values like:

DB_URL=jdbc:mysql://(name db):3306/yalotengo?createDatabaseIfNotExist=true

DB_USER=(your user)

DB_PASSWORD=(your password)

SERVER_PORT=8080

## 3. Configure application.properties: 
#### In src/main/resources/application.properties make sure you have:

spring.application.name=yolatengo

spring.output.ansi.enabled=ALWAYS

spring.config.import=optional:file:.env[.properties]

spring.datasource.url=${DB_URL}

spring.datasource.username=${DB_USER}

spring.datasource.password=${DB_PASSWORD}

#### (For development, uncomment ONE of the following:)
#### spring.jpa.hibernate.ddl-auto=create
#### spring.jpa.hibernate.ddl-auto=update
#### spring.jpa.hibernate.ddl-auto=validate
### --- and continue:

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=${SERVER_PORT}

--------------

### Start MySQL (Docker example):
docker run --name mysql-yalotengo -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=yalotengo -p 3306:3306 -d mysql:8.0
### Run the application:
./mvnw spring-boot:run

## 🛠️ Some possible troubleshooting

Port already in use → change SERVER_PORT in .env and restart.

Access denied for user → make sure DB_USER and DB_PASSWORD in .env match your MySQL setup.

Schema not created → for development, set spring.jpa.hibernate.ddl-auto=create or update in application.properties.

Database logs → check MySQL health with:

docker compose logs db


### ✨ That’s it! Clone → configure .env → run docker compose up --build → open http://localhost:8080.

## 👩‍💻 Developed by Bruna Sonda

### https://github.com/brunasonda 


----
### Contributions are welcome! 🚀

Contributions are welcome! If you want to contribute:

- Fork this repository.

- Create a feature branch (git checkout -b feature/your-feature-name).

- Commit your changes (git commit -m "Add your message here").

- Push to your branch (git push origin feature/your-feature-name).

- Open a Pull Request with a clear title and description of your changes.

Please make sure to add a short comment in your Pull Request explaining the motivation behind your contribution. 🚀
