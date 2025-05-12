# Spring Boot Quiz Application

This is a Spring Boot-based application backed by PostgreSQL that provides core functionalities to manage questions and quizzes. It follows a clean, modular architecture using layered components.

## 📌 Features

### 🧾 Question Management
- `GET /questions`: Retrieve all questions.
- `GET /questions/category/{category}`: Get questions filtered by category.
- `POST /questions`: Add a new question.

### 🧠 Quiz Management
- `POST /quiz`: Create a new quiz using selected questions.
- `GET /quiz/{id}`: Retrieve questions in a quiz.
- `POST /quiz/submit`: Submit answers and calculate quiz results.

---

## 🏗️ Architecture Overview

The application follows a 4-layered architecture:

### 1. **Controller Layer**
Handles HTTP requests and responses, acting as the bridge between the frontend and backend.

### 2. **Service Layer**
Contains the business logic to process data before interacting with the database.

### 3. **DAO Layer** 💾
Manages database operations using **Spring Data JPA**, providing easy access to PostgreSQL.

### 4. **Model Layer** 📄
Defines the structure of database tables and relationships using JPA annotations.

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

---

## 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
