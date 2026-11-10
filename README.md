# API Automation — REST Assured + TestNG

API automation framework with a reusable client layer, TestNG tests, JSON Schema validation and an importable Postman collection.

## API under test
https://jsonplaceholder.typicode.com/

JSONPlaceholder is a public fake REST API. It is intended for testing and prototyping. Write operations return simulated responses and do not persist changes.

## Prerequisites
- Java 17+
- Maven 3.9+

## Run
```bash
mvn clean test
```
Override the API host if needed:
```bash
mvn clean test -DbaseUrl=https://jsonplaceholder.typicode.com
```

## Included coverage
- GET post by ID
- Filter posts by user ID
- POST create representation
- PUT update representation
- DELETE response
- 404 for unknown post
- JSON Schema contract validation

## Postman
Import:
- `postman/JSONPlaceholder-Posts.postman_collection.json`
- `postman/JSONPlaceholder.postman_environment.json`

Select the `JSONPlaceholder Local` environment and run the collection.

## Framework design
- `ApiConfig`: centralized base URL
- `PostsClient`: reusable API request methods
- `tests/`: endpoint and contract tests
- `schemas/`: JSON Schema contract
- `postman/`: manual/API exploration collection

## Notes
- Do not put real tokens or secrets in source control.
- Add negative cases, response-time thresholds, auth scenarios and environment-specific data as required by a real client project.
