# IoT Monitoring System

We are building an **IoT monitoring system** to collect, process, and analyze device data.  
The project has already been started, and we are looking for it to be completed.

---

## 📋 Project Tasks

A detailed project description and the remaining tasks can be found in the [TASKS.md](./TASKS.md) file located in the root directory.

---

## 🚀 Assignment Instructions

1. Clone this repository
2. Review the [TASKS.md](./TASKS.md) file.
3. Complete the remaining implementation tasks.
4. Ensure the project is working end-to-end.
5. Upload the finished project into your own **GitHub repository**.
5. Share the repository link with us.

---

## ⏰ Timeline

Please complete the assignment **within one week**.

---

## 📂 Project Structure (expected)


The project utilises a standard Spring Boot architecture layout:

| Layer          | Responsibility                                                     |
|----------------|--------------------------------------------------------------------|
| **Controller** | Exposes REST API endpoints and handles incoming requests.          |
| **DTO**        | Handles data exchange between API and services.                    |
| **Entity**     | Defines domain models and relationships mapped to database tables. |
| **Messaging**  | Handles asynchronous communication through Kafka.                  |
| **Repository** | Uses Spring Data JPA for persistence.                              |
| **Service**    | Contains the core business logic, validation, and data processing. |
