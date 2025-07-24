# NeutronDB API

![Java](https://img.shields.io/badge/Java-24-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/build-Maven-red.svg)
![License](https://img.shields.io/badge/license-MIT-lightgrey.svg)

NeutronDB is a RESTful API backend built with Spring Boot. It's designed to store and manage video game performance reports, with a special focus on compatibility across different operating systems and hardware configurations. This backend provides all the necessary endpoints to create, read, update, and delete (CRUD) games and their associated performance reports.

This project is intended as the data source for the [NeutronDB Frontend](https://github.com/YOUR_USERNAME/YOUR_FRONTEND_REPO_NAME) application.

## ✨ Features

- **Game Management:** Add, update, delete, and list games in the database.
- **Report Management:** Create and manage detailed performance reports for specific games.
- **RESTful Architecture:** Provides an intuitive API using standard HTTP methods (`GET`, `POST`, `PUT`, `DELETE`).
- **DTO Layer:** Utilizes the Data Transfer Object (DTO) pattern for clean and secure data transfer.
- **Service Layer:** All business logic is encapsulated within the service layer.
- **Database Agnostic:** Built with Spring Data JPA, allowing for easy transition from the default H2 in-memory database to others like PostgreSQL or MySQL.

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 24
- **Data Persistence**: Spring Data JPA
- **Database**: H2 In-Memory Database (for easy setup)
- **API**: Spring Web (REST Controllers)
- **Utilities**: Lombok, Spring Boot DevTools
- **Build Tool**: Apache Maven

## 🚀 Setup and Installation

Follow these steps to get the project running on your local machine.

### Prerequisites

- JDK 24 or later
- Apache Maven
- Git

### Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git](https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git)
    cd YOUR_REPO_NAME
    ```

2.  **Compile and run the project using Maven:**
    ```bash
    mvn spring-boot:run
    ```

3.  The application will start on the default port `8080`.
    - You can verify that the API is running by navigating to `http://localhost:8080/games` in your browser. You should see an empty array (`[]`).
    - You can access the H2 database console at `http://localhost:8080/h2-console` (see `application.properties` for connection details).

## API Endpoints

### 🎮 Game Endpoints

| HTTP Method | Endpoint          | Description                          |
|-------------|-------------------|--------------------------------------|
| `GET`       | `/games`          | Retrieves a list of all games.       |
| `GET`       | `/games/{id}`     | Retrieves a specific game by its ID. |
| `POST`      | `/games`          | Adds a new game.                     |
| `PUT`       | `/games/{id}`     | Updates an existing game by its ID.  |
| `DELETE`    | `/games/{id}`     | Deletes a specific game by its ID.   |

### 📝 Report Endpoints

| HTTP Method | Endpoint          | Description                            |
|-------------|-------------------|----------------------------------------|
| `GET`       | `/reports`        | Retrieves a list of all reports.       |
| `GET`       | `/reports/{id}`   | Retrieves a specific report by its ID. |
| `POST`      | `/reports`        | Adds a new report.                     |
| `PUT`       | `/reports/{id}`   | Updates an existing report by its ID.  |
| `DELETE`    | `/reports/{id}`   | Deletes a specific report by its ID.   |

### Example Requests

**To add a new game via `POST /games`:**
```bash
curl -X POST http://localhost:8080/games \
-H "Content-Type: application/json" \
-d '{
    "name": "Baldur''s Gate 3",
    "developer": "Larian Studios",
    "genre": "CRPG",
    "platforms": ["WINDOWS", "MACOS"],
    "deckVerifiedStatus": true
}'
```

**To add a new report via `POST /reports`:**
```bash
curl -X POST http://localhost:8080/reports \
-H "Content-Type: application/json" \
-d '{
    "gameId": 1,
    "body": "Runs great with Proton-GE. Performance is excellent even in Act 3.",
    "verdict": "GOLD",
    "instability": "STABLE",
    "overallRating": "9/10",
    "multiplayerRating": "8/10",
    "distro": "EndeavourOS",
    "kernel": "6.9.9-zen1-1-zen",
    "ramGb": 32,
    "gpuDriver": "Mesa 24.1.4",
    "gpuModel": "AMD Radeon RX 7800 XT",
    "cpuModel": "AMD Ryzen 7 7800X3D"
}'
```

## ⚙️ Configuration

The project uses an H2 in-memory database by default. You can change the database configuration in the `src/main/resources/application.properties` file.

**Default H2 Configuration:**
```properties
# H2 Database Settings
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Data Source Settings
spring.datasource.url=jdbc:h2:mem:neutrondb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA Settings
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for more details.