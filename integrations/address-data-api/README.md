# Address Data API

## Overview
The Address Data API is a Spring Boot application designed to manage address data. It provides a RESTful interface for creating, retrieving, updating, and deleting address records. The API includes fields for street, number, neighborhood, city, and state.

## Features
- **CRUD Operations**: Create, Read, Update, and Delete address records.
- **Data Persistence**: Uses JPA for data persistence with a relational database.
- **Sample Data**: Pre-populated with sample address data for testing and demonstration purposes.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher

### Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd address-data-api
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
http://localhost:8080/api/address
```

### Sample Requests
- **Get all addresses**: `GET /api/address`
- **Get address by ID**: `GET /api/address/{id}`
- **Create a new address**: `POST /api/address`
- **Update an address**: `PUT /api/address/{id}`
- **Delete an address**: `DELETE /api/address/{id}`

## Database Initialization
The application includes a `data.sql` file that initializes the database with sample address data upon startup.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.