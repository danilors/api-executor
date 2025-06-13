# Asset Data API

## Overview
The Asset Data API is a Spring Boot application designed to manage asset-related data. It provides a RESTful interface for performing CRUD operations on assets.

## Project Structure
The project follows a standard Maven structure with the following key directories:

- **src/main/java**: Contains the main application code.
  - **br/com/api/asset/asset_data_api**: The package for the application.
    - **AssetDataApiApplication.java**: The entry point of the application.
    - **controller**: Contains the REST controllers.
    - **entity**: Contains the data model classes.
    - **repository**: Contains the repository interfaces for data access.
    - **service**: Contains the service classes for business logic.

- **src/main/resources**: Contains application configuration files.
  - **application.properties**: Configuration properties for the Spring Boot application.

- **src/test/java**: Contains test cases for the application.

- **pom.xml**: The Maven configuration file for managing dependencies and build settings.

## Setup Instructions
1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd asset-data-api
   ```

2. **Build the project**:
   ```
   mvn clean install
   ```

3. **Run the application**:
   ```
   mvn spring-boot:run
   ```

## Usage
Once the application is running, you can access the API endpoints for asset management. The base URL for the API is `http://localhost:8080/api/assets`.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.