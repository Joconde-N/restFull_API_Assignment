
---

## 📄 `question6-bonus-user-api/README.md`

```md
# Bonus Question – User Profile API

## Description
This API manages user profiles and supports CRUD operations,
searching by username, country, or age range,
activating and deactivating users,
and returning responses wrapped in a custom ApiResponse object.

## How to Run
1. Open the project in your IDE.
2. Run the Spring Boot main application class.
3. API base URL:
   http://localhost:8080

## Endpoints

### Get all users
GET /api/users

### Get user by ID
GET /api/users/{id}

### Create user
POST /api/users

### Update user
PUT /api/users/{id}

### Delete user
DELETE /api/users/{id}

### Search users
GET /api/users/search?username=john  
GET /api/users/search?country=Rwanda  
GET /api/users/search?minAge=20&maxAge=25  

### Activate user
PATCH /api/users/{id}/activate

### Deactivate user
PATCH /api/users/{id}/deactivate

## Testing
All endpoints were tested using Postman.
Responses are returned using a custom ApiResponse wrapper.
