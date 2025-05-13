**Technologies**

Java 17
Spring Boot 3
PostgreSQL
Spring Data JPA
SLF4J with Logback for logging
Docker and Docker Compose
Maven for dependency management

**Requirements**

To run this project, ensure you have the following installed:

Java 17
Maven 3.8+
Docker and Docker Compose
PostgreSQL (optional if running locally without Docker)
Postman or curl for testing API endpoints

**Installation and Setup**

Clone the repository:
```
git clone https://github.com/DaniilHelenka/tech_spec_java_spring_final.git
```
**Running the Application**

```
mvn clean install
mvn spring-boot:run
docker-compose up --build
```

The application will be available at http://localhost:8080.
PostgreSQL will run in a separate container and be accessible at localhost:5432.

**User Endpoints**

POST /users
Create a new user.
Request Body:
json

```
{
"name": "John Doe",
"email": "john.doe@example.com"
}
```
Response: 201 Created with user details.
GET /users/{id}
Retrieve user information by ID.
Response:
json
```
{
"id": 1,
"name": "John Doe",
"email": "john.doe@example.com",
"createdAt": "2025-05-13T12:00:00"
}
```
PUT /users/{id}
Update user information.
Request Body:
json

```
{
"name": "John Smith",
"email": "john.smith@example.com"
}
```
Response: 200 OK with updated user details.
DELETE /users/{id}
Delete a user by ID.
Response: 204 No Content.
Subscription Endpoints
POST /users/{id}/subscriptions
Add a subscription for a user.
Request Body:
json

```
{
"serviceName": "Netflix"
}
```
Response: 201 Created with subscription details.
GET /users/{id}/subscriptions
Retrieve all subscriptions for a user.
Response:
json

```
[
{
"id": 1,
"serviceName": "Netflix",
"createdAt": "2025-05-13T12:00:00"
}
]
```
DELETE /users/{id}/subscriptions/{sub_id}
Delete a subscription by ID.
Response: 204 No Content.
GET /subscriptions/top
Retrieve the top 3 most popular subscriptions.
Response:
json

```
[
{"serviceName": "Netflix", "count": 10},
{"serviceName": "YouTube Premium", "count": 8},
{"serviceName": "Yandex.Plus", "count": 5}
]
```
