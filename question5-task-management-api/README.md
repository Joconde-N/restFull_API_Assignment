
---

```md
# Question 5 – Task Management API

## Description
This project implements a simple task/to-do list REST API. The API supports listing all tasks, getting a task by ID, filtering tasks by completion status, filtering by priority, creating a task, updating a task, marking a task as completed, and deleting a task. Data is stored in memory using a list.

## How to Run
1. Open the project in your IDE.
2. Select the correct JDK installed on your PC.
3. Run the Spring Boot main class (the class with `@SpringBootApplication`).
4. The app runs at: `http://localhost:8080`

## Endpoints (with Samples)

### Get all tasks
- Method: GET  
- URL: /api/tasks

### Get task by ID
- Method: GET
- URL: /api/tasks/1

### Get tasks by completion status
- Method: GET
- URL: /api/tasks/status?completed=false

### Get tasks by priority
- Method: GET
- URL: /api/tasks/priority/LOW

### Create a new task
- Method: POST
- URL: /api/tasks

### Update a task
- Method: PUT
- URL: /api/tasks/1

### Mark a task as completed
- Method: PATCH
- URL: /api/tasks/1/complete

### Delete a task
- Method: DELETE
- URL: /api/tasks/2


