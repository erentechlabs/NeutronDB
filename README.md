# NeutronDB API by MasterCockatoo

![Java](https://img.shields.io/badge/Java-24-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/build-Maven-red.svg)

NeutronDB is a RESTful API backend built with Spring Boot. It's designed to store and manage video game performance reports, with a special focus on compatibility across different operating systems and hardware configurations. This backend provides all the necessary endpoints to create, read, update, and delete (CRUD) games and their associated performance reports.

This project is intended as the data source for the [NeutronDB Frontend](https://github.com/MasterCockatoo/NeutronDB-Frontend) application.

## 🖼️ Application Preview

A preview of the NeutronDB frontend in action. 

*(**Note:** Replace the placeholder image below with an actual screenshot or GIF of your running application. You can drag and drop an image directly into the GitHub text editor to upload it.)*

![NeutronDB Frontend Preview](https://raw.githubusercontent.com/MasterCockatoo/NeutronDB/main/docs/screenshot.png) 
// You need to replace this link with the link of the screenshot you uploaded to github.


## ✨ Features

- **Game Management:** Add, update, delete, and list games in the database.
- **Report Management:** Create and manage detailed performance reports for specific games.
- **RESTful Architecture:** Provides an intuitive API using standard HTTP methods (`GET`, `POST`, `PUT`, `DELETE`).
- **DTO Layer:** Utilizes the Data Transfer Object (DTO) pattern for clean and secure data transfer.
- **Service Layer:** All business logic is encapsulated within the service layer.
- **Database Agnostic:** Built with Spring Data JPA, allowing for easy configuration with major SQL databases like PostgreSQL, MySQL, and others.

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 24
- **Data Persistence**: Spring Data JPA / Hibernate
- **API**: Spring Web (REST Controllers)
- **Utilities**: Lombok, Spring Boot DevTools
- **Build Tool**: Apache Maven

## 🚀 Setup and Installation

Follow these steps to get the project running on your local machine.

### Prerequisites

- JDK 24 or later
- Apache Maven
- A running SQL database instance (e.g., PostgreSQL, MySQL)

### Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/MasterCockatoo/NeutronDB.git](https://github.com/MasterCockatoo/NeutronDB.git)
    cd NeutronDB
    ```

2.  **Configure the database connection:**
    - Open the `src/main/resources/application.properties` file.
    - Add the connection properties for your database, including the URL, username, and password. See the **Configuration** section below for an example using PostgreSQL.

3.  **Compile and run the project using Maven:**
    ```bash
    mvn spring-boot:run
    ```

4.  The application will start on the default port `8080`. You can now send requests to the API endpoints.

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
    "name": "Elden Ring",
    "developer": "FromSoftware",
    "genre": "Action RPG",
    "platforms": ["WINDOWS"],
    "deckVerifiedStatus": true
}'
```

**To add a new report via `POST /reports`:**
```bash
curl -X POST http://localhost:8080/reports \
-H "Content-Type: application/json" \
-d '{
    "gameId": 1,
    "body": "Runs flawlessly on high settings with a stable frame rate.",
    "verdict": "PLATINUM",
    "instability": "STABLE",
    "overallRating": "10/10",
    "multiplayerRating": "8/10",
    "distro": "Fedora 40",
    "kernel": "6.9.5-200.fc40.x86_64",
    "ramGb": 32,
    "gpuDriver": "Mesa 24.1.2",
    "gpuModel": "AMD Radeon RX 6600",
    "cpuModel": "AMD Ryzen 5 3600"
}'
```

## ⚙️ Configuration

This project requires a connection to an SQL database. You must provide the configuration details in the `src/main/resources/application.properties` file.

Below is a sample configuration for connecting to a **PostgreSQL** database.

```properties
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password

# JPA / Hibernate Settings
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# When set to "update", Hibernate will automatically update the database schema
# based on your entities. Use "validate" in production.
spring.jpa.hibernate.ddl-auto=update
```
*Remember to replace the placeholder values (`your_database_name`, `your_postgres_username`, `your_postgres_password`) with your actual database credentials.*
