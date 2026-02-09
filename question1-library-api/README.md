# Question 1 – Library Book Management API

## Description
This project implements a simple REST API for managing library books.
The API allows users to view all books, get a book by ID, search books by title,
add new books, and delete books. Data is stored in memory using a list.

## How to Run
1. Open the project in your IDE.
2. Ensure the correct JDK is selected.
3. Run the main Spring Boot application class.
4. The application runs on:
   http://localhost:8080

## Endpoints

### Get all books
- Method: GET 
- URL: /api/books

### Get book by ID
- Method: GET 
- URL: /api/books/{id}

### Search book by title
- Method: GET 
- URL: /api/books/search?title=clean

### Delete book
- Method: DELETE 
- URL: /api/books/{id}

### Add new book
POST /api/books

Sample Request:
```json
{
  "title": "Clean Code",
  "author": "Robert Martin",
  "isbn": "978-0132350884",
  "publicationYear": 2008
}

