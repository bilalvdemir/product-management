# Product Management Service

This project is a small Java Spring Boot application for managing products.  
Users can add, update, delete, and get products using REST API.  
The system can handle different product types like **Books**, **Courses**, and **Magazines**.

---

## Features

- **Product Management**  
  Create, read, update, and delete products with Rest API.

- **Product Types**
    - Book
    - Course
    - Magazine

- **Search and Filter**  
  Find products by title or keyword, and apply pagination or sorting for refined results.

- **Validation and Error Handling**  
  All inputs are validated before saving.  
  Errors return clear messages to the client.

---

## Architecture and Design Choices

This project is built using **Layered Architecture** with **Domain-Driven Design (DDD)** principles.  
It is not a full **Hexagonal Architecture**, but it follows a similar idea.  
Layers are separated and communicate through clear interfaces (ports).  
This helps keep the codebase clean, easy to maintain, and extend in the future.

### Main Technologies and Concepts

- **Spring Boot 3 & Java 21**  
  Used as the main framework for creating REST APIs, managing dependencies, and application lifecycle.

- **Layered Architecture (DDD Style)**  
  The application is structured into four layers:
    - **Presentation Layer** → REST Controllers and DTO Mappers.
    - **Application Layer** → Services and Ports for business use cases.
    - **Domain Layer** → Core business logic, Entities, Factories, and Repositories.
    - **Infrastructure Layer** → JPA entities, repository adapters, and mappers.

- **Domain-Driven Design (DDD)**  
  Business logic stays in the domain layer.  
  The domain defines entities like `Product`, `Book`, `Course`, and `Magazine`,  
  and uses value objects such as `ProductData`.

- **Factory Pattern**  
  Each product type has its own factory (`BookFactory`, `CourseFactory`, `MagazineFactory`) to create and update products.  
  Factories are managed by `ProductFactoryRegistry`, which selects the correct one based on product type.

- **Caching**  
  Frequently accessed products are cached using **Spring Cache**, improving performance for read operations.

- **H2 Database**  
  The project uses an **in-memory H2 database** for development and testing.  
  It’s lightweight, fast, and does not require external setup.

- **Spring Data JPA**  
  Used for persistence layer implementation.  
  Repositories are defined as interfaces, while `ProductRepositoryAdapter` acts as an adapter to connect the domain with JPA.

- **Spring Validation (Jakarta Validation)**  
  Used for validating DTO fields like price, title, and stock.

- **Springdoc OpenAPI / Swagger**  
  Provides auto-generated API documentation and interactive UI for testing endpoints.

- **Resilience4j**  
  Used for rate limiting in the REST controller to control request flow and protect the system.

- **Micrometer**  
  Used for metrics and performance monitoring (e.g., timing product creation and fetching).

- **Spring Cache**  
  Integrated caching layer to store frequently used data like products by ID.

- **Lombok**  
  Reduces boilerplate code (getters, setters, builders, constructors, logging).

- **Testcontainers**  
  Used for integration testing with real containers.

- **JUnit 5 & Mockito**  
  Used for unit testing services, factories, and repositories.

- **Slf4j**  
  Logging framework integrated with Spring Boot for clean structured logs.

---

## Scalability and Future Improvements

Right now, the system uses **Layered Architecture**, but it can evolve into **Hexagonal Architecture** if needed.  
For larger systems:
- PostgreSQL can replace H2 for persistent data storage.
- Elasticsearch can be added for fast searching.
- Kafka or Debezium can synchronize data between services.

This would allow better scalability for read-heavy workloads while keeping the application modular.

---

## How to Run the Application

Follow these steps to run the application:

1. Clone the repository.
2. Make sure **Java 21**, **Maven**, and optionally **Docker** are installed.
3. Open the terminal and navigate to the project directory:

   ```bash
   cd product-management-service/

4. To run with Maven directly:
   ```bash
   ./mvnw spring-boot:run

The app will start on http://localhost:8080 and use the in-memory H2 database.

If you prefer Docker:
1. Make sure Docker is running.
2. Build docker image command:
   ```bash
   docker build -t product-management:latest .
3. Start docker compose with the following command:

   ```bash
   docker-compose up

This will start the Product Management Service container and H2 in memory DB.

## API Documentation

The API is documented with **OpenAPI (Swagger)**.  
To open the Swagger UI:

1. Start the application.
2. Open your browser and go to:  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
3. You can view and test all API endpoints from this interface.
4. H2 Web UI [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
5. JDBC Url: [jdbc:h2:mem:product]()
---

## Testing

The project includes both **unit tests** and **integration tests**.  
Tests cover the following areas:

- Domain validation and business rules.
- Factory creation and update logic.
- Repository persistence behavior.
- Controller endpoint tests with mock requests.

To run all tests, execute the following command:

```bash
  ./mvnw clean verify
