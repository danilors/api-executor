# Personal Data API

## Overview

The Personal Data API is a Spring Boot application designed to manage personal data records. It provides a RESTful interface for creating, retrieving, updating, and deleting personal records. The API includes fields for name, email, birthdate, and phone number.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete personal data records.
- **Data Persistence**: Uses JPA for data persistence with a relational database.
- **Sample Data**: Pre-populated with sample pnersonal data for testing and demonstration purposes.

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd personal-data-api
   ```
3. Build the project using Maven:
   ```
   ./mvnw clean install
   ```

### Running the Application

To run the application, use the following command:

```
./mvnw spring-boot:run
```

### Accessing the API

Once the application is running, you can access the API at:

```
http://localhost:8080/api/personal
```

### Sample Requests

- **Get all personal data**: `GET /api/personal`
- **Get personal data by ID**: `GET /api/personal/{id}`
- **Create a new personal record**: `POST /api/personal`
- **Update a personal record**: `PUT /api/personal/{id}`
- **Delete a personal record**: `DELETE /api/personal/{id}`

## Database Initialization

The application includes a `data.sql` file that initializes the database with sample personal data upon startup.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
