# 📚 Libropolis Backend

Backend for the **Libropolis** project, an application developed with **Spring Boot** for managing a digital library. It handles users, books, purchases, and balance top-ups, ensuring stock and balance validations.

---

## 🚀 Technologies Used
- ☕ Java 17
- 🌱 Spring Boot
- 🛠️ Spring Data JPA
- 🐘 MySQL
- 🔄 Maven

---

## ⚙️ Features

### 👥 Users
- User registration.
- Update user information.
- Balance top-up with limit validation.

### 📖 Books
- Search books by title and ISBN.
- Stock management.
- Retrieve all available books.

### 🛒 Purchases
- Stock validation during purchase.
- Balance validation for the user.
- Purchase history by user.

---

## 🔌 Main Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/usuario/{usuarioId}` | Get user information |
| PUT | `/usuario/actualizar/{usuarioId}` | Update user information |
| POST | `/usuario/recargar/{usuarioId}` | Top-up user balance |
| GET | `/libro/titulo/{titulo}` | Search book by title |
| GET | `/libro/isbn/{isbn}` | Search book by ISBN |
| POST | `/compra` | Register a purchase |
| GET | `/compra/usuario/{usuarioId}` | Get purchases by user |

---

Libropolis Team
Created by Carlos Andres Montoya Murcia.
Email: cmurciamontoya2002@gmail.com

## 🏗️ How to Run the Project

1️⃣ Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/LibropolisBackend.git


