
---

# 📄 `question2-student-api/README.md`

```md
# Question 2 – Student Registration API

## Description
This project provides a REST API for managing student registration and information.
The API supports viewing students, filtering by major and GPA, adding new students,
and updating student information.

## How to Run
1. Open the project in your IDE.
2. Run the Spring Boot main application class.
3. API base URL:
   http://localhost:8080

## Endpoints

### Get all students
- Method: GET 
- URL: /api/students

### Get student by ID
- Method: GET 
- URL: /api/students/101

### Get students by major
- Method: GET 
- URL: /api/students/major/ComputerScience

### Filter students by GPA
- Method: GET 
- URL: /api/students/filter?gpa=3.5

### Update student
- Method: PUT 
- URL: /api/students/101

### Register a new student
- Method: POST
- URL: /api/students

