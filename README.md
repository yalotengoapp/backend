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

## 👀 Stay Tuned

More features and refinements are coming soon. Contributions are welcome! 🚀
