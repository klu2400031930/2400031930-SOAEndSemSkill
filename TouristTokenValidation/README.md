# TouristTokenValidation

Java 21, Spring Boot 4.0.1, PostgreSQL project. Includes user registration, login/JWT generation and Tourist CRUD.

1. Create PostgreSQL database `touristdb`.
2. Update DB username/password in `src/main/resources/application.properties`.
3. Import as **Existing Maven Project** in Eclipse and run Maven > Update Project.
4. Run `TouristTokenValidationApplication.java`.
5. Open `http://localhost:8082/swagger-ui/index.html`.

Workflow:
- `POST /api/auth/register` body: `{"username":"sairam","password":"test123"}`
- `POST /api/auth/login` body: `{"username":"sairam","password":"test123"}`. Copy `token` from response.
- Use custom `Token` header with that JWT for every Tourist endpoint. Paste raw token; `Bearer ` prefix is also accepted.

Tourist endpoints:
- POST `/api/tourists` add
- GET `/api/tourists` list
- GET `/api/tourists/{id}` retrieve one (token checked before repository lookup)
- PUT `/api/tourists/{id}` update
- DELETE `/api/tourists/{id}` delete

Example Tourist JSON:
{"name":"Ravi Kumar","email":"ravi@gmail.com","country":"India","contact":"9876543210"}

The Tourist field names are example fields; align them with your instructor's actual schema if different. This is a coursework implementation. The invalid-token response is 500 to match the assignment brief; production APIs normally use 401.
