# Expense Tracker

### A simple Spring Boot application for managing personal expenses.

## Features

- **Add Expenses**: Create a new expense by specifying a description, amount, and date.
- **View All Expenses**: Retrieve a list of all expenses stored in the application.
- **View Expense by ID**: Retrieve details of a specific expense using its ID.
- **Delete Expenses**: Remove an expense by ID.

## Technologies Used

- **Java 11+**
- **Spring Boot**: For building the RESTful API.
- **Spring Data JPA**: To handle database operations.
- **H2 Database**: In-memory database for simplicity and testing.
- **Maven**: For dependency management and building the project.
- **Lombok** (optional): To reduce boilerplate code for model classes.

## Getting Started

### Prerequisites

- **Java 11** or later installed on your machine.
- **Maven** installed (optional if you're using an IDE with built-in Maven support).
- **Git** installed for version control (to clone and manage the project).
- **Postman** or **curl** (optional, for testing the API endpoints).

### Installation

1. **Clone the repository**:

    ```bash
    git clone https://github.com/your-username/expense-tracker.git
    cd expense-tracker
    ```

2. **Build the project** (if using Maven):

    ```bash
    mvn clean install
    ```

3. **Run the application**:

    ```bash
    mvn spring-boot:run
    ```

    Alternatively, if you are using an IDE like IntelliJ or Eclipse, you can run the `ExpenseTrackerApplication.java` file directly.

4. **Access the API**:

    The API will be available at `http://localhost:8080`.

    You can use Postman or a similar tool to interact with the API.

## API Endpoints

### 1. Add a New Expense

- **URL**: `/api/expenses`
- **Method**: `POST`
- **Request Body**:

    ```json
    {
        "description": "Groceries",
        "amount": 50.0,
        "date": "2024-09-20"
    }
    ```

- **Response**: Returns the created expense object.

### 2. Get All Expenses

- **URL**: `/api/expenses`
- **Method**: `GET`
- **Response**: Returns a list of all expenses.

### 3. Get Expense by ID

- **URL**: `/api/expenses/{id}`
- **Method**: `GET`
- **Response**: Returns the expense with the specified ID or a `404 Not Found` if the expense doesn't exist.

### 4. Delete an Expense

- **URL**: `/api/expenses/{id}`
- **Method**: `DELETE`
- **Response**: Deletes the expense with the specified ID. Returns `204 No Content` on success, or `404 Not Found` if the expense doesn't exist.

## H2 Database Console

To view the in-memory H2 database, visit the following URL after running the application:

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

Ensure the application is running, and then you can view or query the database using the H2 console.

## Project Structure

```bash
expense-tracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/expensetracker/
│   │   │       ├── Expense.java              # Expense entity class
│   │   │       ├── ExpenseRepository.java    # Repository interface for CRUD operations
│   │   │       ├── ExpenseService.java       # Service layer for business logic
│   │   │       ├── ExpenseController.java    # Controller for REST API
│   │   │       └── ExpenseTrackerApplication.java  # Main class to start the Spring Boot application
│   │   └── resources/
│   │       └── application.properties        # Database configuration (H2)
├── pom.xml                                    # Maven dependencies and build configuration
└── README.md                                  # Project documentation
```

## Configuration

### `application.properties`

Located in `src/main/resources/application.properties`. It contains configuration settings for the database and other Spring Boot properties.

```properties
# H2 Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.