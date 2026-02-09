
---

```md
# Question 3 – Restaurant Menu API

## Description
This project implements a REST API for managing restaurant menu items. The API supports listing all menu items, getting an item by ID, filtering items by category, viewing available items, searching by name, adding new items, toggling item availability, and deleting items. Data is stored in memory using a list.

## How to Run
1. Open the project in your IDE.
2. Select the correct JDK installed on your PC.
3. Run the Spring Boot main class (the class with `@SpringBootApplication`).
4. The app runs at: `http://localhost:8080`

## Endpoints (with Samples)

### Get all menu items
- Method: GET  
- URL: /api/menu

### Get menu item by ID
- Method: GET
- URL: /api/menu/1

### Get menu item by ID
- Method: GET
- URL: /api/menu/1

### Search menu items by name
- Method: GET
- URL: /api/menu/search?name=coffee

### Add a new menu item
- Method: POST
- URL: /api/menu

### Toggle item availability
- Method: PUT
- URL: /api/menu/1/availability

### Delete menu item
- Method: DELETE
- URL: /api/menu/2

### Sample Response:
- Status: 204 No Content

