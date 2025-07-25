# 🍽️ Restaurant Management System

A full-stack web application for managing restaurant operations, built using **Spring Boot** for the backend and **Angular** for the frontend.

---

## 📁 Project Structure


---

## 🚀 Features

### Backend (Java Spring Boot)
- RESTful API development using **Spring Boot**
- Authentication and authorization with **Spring Security**
- Clean and modular code with **Lombok**
- Object mapping using **MapStruct**
- Layered architecture (Controller → Service → Repository)
- Secure password handling (e.g., BCrypt)
- Role-based access control
- Token-based authentication (e.g., JWT)

### Frontend (Angular)
- Responsive, modern UI built with Angular
- Routing and lazy loading
- Component-based architecture
- Reactive forms with validation
- Integration with secured backend API
- Dynamic views for menu, orders, and reservations

---

## ⚙️ Tech Stack

| Layer     | Technologies                                |
|-----------|---------------------------------------------|
| Backend   | Java, Spring Boot, Spring Security, Lombok, MapStruct |
| Frontend  | Angular, TypeScript, HTML, SCSS             |
| Database     | Oracle Database (via Hibernate ORM)                            |
| Build Tools | Maven, Angular CLI                        |
| Security  | JWT, BCrypt                                 |

---

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Node.js & npm
- Angular CLI (`npm install -g @angular/cli`)
- Maven
- MySQL / PostgreSQL (or use embedded H2)

---

### 🚧 Backend Setup

```bash
cd backend

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

### 🚧 Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Run the Angular app
ng serve
```
