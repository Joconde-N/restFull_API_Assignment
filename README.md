# RESTful API Assignment – Student ID: 26441

## Overview
This repository contains multiple Spring Boot RESTful API projects developed as part of a REST API assignment.  
Each question was implemented as a **separate Spring Boot project**, focusing only on **REST controllers** and **in-memory data storage** (no database, no service layer, no repository layer).

All APIs were implemented following proper REST principles, HTTP methods, and status codes.

---

## Technologies Used
- Java
- Spring Boot
- Spring Web
- Postman (API testing)

---

## Project Structure
Each question is implemented as an independent Spring Boot project:

- question1-library-api
- question2-student-api
- question3-restaurant-menu-api
- question4-ecommerce-product-api
- question5-task-api
- question6-bonus-user-api
- Rest API Assignment - 26441 (Postman collection)


Each project contains:
- Controller package
- Model package
- Main Spring Boot application class
- README.md file describing endpoints and usage

---

## Implemented Projects Summary

### Question 1 – Library Book Management API
Implemented a REST API to manage library books.
- View all books
- Get book by ID
- Search books by title
- Add new books
- Delete books
- Used HTTP status codes: 200, 201, 204, 404

---

### Question 2 – Student Registration API
Implemented a REST API for managing student information.
- View all students
- Get student by ID
- Filter students by major
- Filter students by minimum GPA
- Register a new student
- Update student information

---

### Question 3 – Restaurant Menu API
Implemented a menu management API for a restaurant.
- View all menu items
- Get menu item by ID
- Filter items by category
- View available items
- Search items by name
- Add new menu items
- Toggle item availability
- Delete menu items

---

### Question 4 – E-Commerce Product API
Implemented a product catalog API for an e-commerce system.
- View all products
- Pagination support
- Get product by ID
- Filter by category and brand
- Search products by keyword
- Filter products by price range
- View in-stock products
- Add new products
- Update product details
- Update stock quantity
- Delete products

---

### Question 5 – Task Management API
Implemented a task (to-do list) management API.
- View all tasks
- Get task by ID
- Filter tasks by completion status
- Filter tasks by priority
- Create new tasks
- Update tasks
- Mark tasks as completed
- Delete tasks

---

### Question 6 – Bonus: User Profile API
Implemented a user profile management API with custom response handling.
- Create, read, update, and delete user profiles
- Search users by username, country, or age range
- Activate and deactivate user profiles
- All responses wrapped using a custom `ApiResponse` object

---

## API Design Notes
- All data is stored in memory using Java `List` collections.
- No database was used.
- No service or repository layers were implemented.
- Controllers handle all logic directly.
- Proper REST annotations were used:
  - `@RestController`
  - `@RequestMapping`
  - `@GetMapping`
  - `@PostMapping`
  - `@PutMapping`
  - `@PatchMapping`
  - `@DeleteMapping`

---

## Testing
- All APIs were tested using **Postman**
- A complete Postman collection is included in the folder:
Rest API Assignment - 26441
- The collection contains requests for **all endpoints in all projects**

---

## How to Run Any Project
1. Open the desired project folder in your IDE.
2. Ensure the correct JDK is selected.
3. Run the main class annotated with `@SpringBootApplication`.
4. Access the API at:
http://localhost:8080


---

## Git Branch
All work is pushed to the following branch:
restFull_api_26441


---

## Conclusion
This assignment demonstrates the implementation of multiple RESTful APIs using Spring Boot, applying Java code while following REST principles, proper HTTP methods, and status codes. All endpoints were fully tested and documented.
