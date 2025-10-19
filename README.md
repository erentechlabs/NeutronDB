# NeutronDB Backend API

![Java](https://img.shields.io/badge/Java-24-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![Maven](https://img.shields.io/badge/build-Maven-red.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)

A RESTful API backend built with Spring Boot for managing video game performance reports across different operating systems and hardware configurations. This backend provides comprehensive CRUD operations for games and their associated performance reports.

## 🖼️ Application Preview

Here are some snapshots of the NeutronDB frontend in action:

<br>

<div align="center">
  
  <h3>Adding a New Game</h3>
  <img width="800" alt="add_game" src="https://github.com/user-attachments/assets/82f1538b-a26f-4887-b1d0-1beade3b9742" />
  
  <br><br>
  
  <h3>Listing Reports for a Cyberpunk 2077</h3>
  <img width="800" alt="list_reports" src="https://github.com/user-attachments/assets/6a3c5746-f5dd-425e-8d28-580b41c95caa" />
  
  <br><br>
  
  <h3>Editing an Existing Report</h3>
  <img width="800" alt="edit_reportt" src="https://github.com/user-attachments/assets/fc49be73-028e-4462-8a15-4ad572a8c846" />
  
  <br><br>
  
  <h3>Creating a New Report</h3>
  <img width="800" alt="new_report" src="https://github.com/user-attachments/assets/a524016d-23be-4490-8995-9df9a6b823bb" />
  
  <br><br>
  
  <h3>Listing Reports - Stardew Valley Example</h3>
  <img width="800" alt="list_reports_stardew" src="https://github.com/user-attachments/assets/64f56515-42cb-40bd-92f2-a2d2fa6ff07e" />
  
</div>

<br>

## 📋 Table of Contents

- [Architecture Overview](#architecture-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Development](#development)

## 🏗️ Architecture Overview

NeutronDB follows a layered architecture pattern:

- **Controller Layer**: RESTful endpoints that handle HTTP requests and responses
- **Service Layer**: Business logic implementation
- **Repository Layer**: Data access using Spring Data JPA
- **Entity Layer**: JPA entities representing database tables
- **DTO Layer**: Data Transfer Objects for clean API contracts
- **Mapper Layer**: Entity-DTO conversion using ModelMapper
- **Exception Handling**: Global exception handler for consistent error responses

## ✨ Features

- **🎮 Game Management**
  - Full CRUD operations for game entities
  - Support for multiple platforms (Windows, Linux, macOS, etc.)
  - Steam Deck verification status tracking
  - Genre and developer information management

- **📝 Performance Report Management**
  - Detailed hardware and software configuration tracking
  - Verdict system (Platinum, Gold, Silver, Bronze, Borked)
  - Stability ratings (Stable, Unstable, Very Unstable)
  - OS distro, kernel, and driver information
  - GPU, CPU, and RAM specifications

- **🔧 Technical Features**
  - RESTful API with proper HTTP methods (GET, POST, PUT, DELETE)
  - DTO pattern for secure data transfer
  - ModelMapper for automatic entity-DTO conversion
  - Global exception handling with custom error responses
  - CORS configuration for frontend integration
  - Input validation with Spring Validation
  - Database agnostic with Spring Data JPA
  - Hot reload with Spring Boot DevTools

## 🛠️ Technology Stack

| Layer | Technology |
|-------|-----------|
| **Framework** | Spring Boot 3.5.3 |
| **Language** | Java 24 |
| **Build Tool** | Apache Maven |
| **Data Persistence** | Spring Data JPA / Hibernate |
| **Database** | PostgreSQL (configurable) |
| **API** | Spring Web (REST Controllers) |
| **Object Mapping** | ModelMapper 3.2.4 |
| **Validation** | Spring Boot Starter Validation |
| **Code Generation** | Lombok |
| **Development** | Spring Boot DevTools |

## 📁 Project Structure

```
backend/NeutronDB/src/main/java/com/neutrondb/neutrondb/
├── config/
│   ├── MapperConfig.java          # ModelMapper configuration
│   └── WebConfig.java              # CORS and web configuration
├── controller/
│   ├── GameController.java         # Game REST endpoints
│   └── ReportController.java       # Report REST endpoints
├── domain/
│   ├── dto/
│   │   ├── GameDTO.java           # Game data transfer object
│   │   └── ReportDTO.java         # Report data transfer object
│   ├── entities/
│   │   ├── GameEntity.java        # Game JPA entity
│   │   └── ReportEntity.java      # Report JPA entity
│   └── enums/
│       ├── Instability.java       # Stability enum
│       ├── Platform.java          # Platform enum
│       └── Verdict.java           # Verdict enum
├── exception/
│   ├── ApiError.java              # Custom error response
│   └── GlobalExceptionHandler.java # Global exception handler
├── mapper/
│   ├── Mapper.java                # Generic mapper interface
│   └── impl/
│       ├── GameMapper.java        # Game entity-DTO mapper
│       └── ReportMapper.java      # Report entity-DTO mapper
├── repository/
│   ├── GameRepository.java        # Game data access
│   └── ReportRepository.java      # Report data access
├── service/
│   ├── GameService.java           # Game business logic
│   └── ReportService.java         # Report business logic
└── NeutronDbApplication.java      # Application entry point
```

## 🚀 Setup and Installation

### Prerequisites

- **JDK 24** or later ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Apache Maven** 3.8+ ([Download](https://maven.apache.org/download.cgi))
- **PostgreSQL** 12+ ([Download](https://www.postgresql.org/download/))

### Installation Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MasterCockatoo/NeutronDB.git
   cd NeutronDB/backend/NeutronDB
   ```

2. **Set up PostgreSQL database:**
   ```sql
   CREATE DATABASE neutrondb;
   ```

3. **Configure database connection:**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/neutrondb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

4. **Build the project:**
   ```bash
   mvn clean install
   ```

5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

6. **Verify the application:**
   
   The server will start on `http://localhost:8080`
   
   Test with: `curl http://localhost:8080/api/v1/games`

## 📡 API Endpoints

All endpoints are prefixed with `/api/v1`

### 🎮 Game Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/api/v1/games` | Retrieve all games | - |
| `GET` | `/api/v1/games/{id}` | Retrieve game by ID | - |
| `POST` | `/api/v1/games` | Create new game | GameDTO |
| `PUT` | `/api/v1/games/{id}` | Update existing game | GameDTO |
| `DELETE` | `/api/v1/games/{id}` | Delete game by ID | - |
| `DELETE` | `/api/v1/games` | Delete all games | - |

### 📝 Report Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/api/v1/reports` | Retrieve all reports | - |
| `GET` | `/api/v1/reports/{id}` | Retrieve report by ID | - |
| `POST` | `/api/v1/reports` | Create new report | ReportDTO |
| `PUT` | `/api/v1/reports/{id}` | Update existing report | ReportDTO |
| `DELETE` | `/api/v1/reports/{id}` | Delete report by ID | - |
| `DELETE` | `/api/v1/reports` | Delete all reports | - |

### 📤 Example Requests

**Create a new game:**
```bash
curl -X POST http://localhost:8080/api/v1/games \
-H "Content-Type: application/json" \
-d '{
  "name": "Elden Ring",
  "developer": "FromSoftware",
  "genre": "Action RPG",
  "platforms": ["WINDOWS", "PLAYSTATION", "XBOX"],
  "deckVerifiedStatus": true
}'
```

**Create a performance report:**
```bash
curl -X POST http://localhost:8080/api/v1/reports \
-H "Content-Type: application/json" \
-d '{
  "gameId": 1,
  "body": "Runs flawlessly on high settings with stable 60 FPS.",
  "verdict": "PLATINUM",
  "instability": "STABLE",
  "overallRating": "10/10",
  "multiplayerRating": "9/10",
  "distro": "Fedora 40",
  "kernel": "6.9.5-200.fc40.x86_64",
  "ramGb": 32,
  "gpuDriver": "Mesa 24.1.2",
  "gpuModel": "AMD Radeon RX 6600",
  "cpuModel": "AMD Ryzen 5 3600"
}'
```

**Get all games:**
```bash
curl http://localhost:8080/api/v1/games
```

**Update a game:**
```bash
curl -X PUT http://localhost:8080/api/v1/games/1 \
-H "Content-Type: application/json" \
-d '{
  "name": "Elden Ring",
  "developer": "FromSoftware",
  "genre": "Action RPG",
  "platforms": ["WINDOWS", "PLAYSTATION", "XBOX"],
  "deckVerifiedStatus": true
}'
```

**Delete a report:**
```bash
curl -X DELETE http://localhost:8080/api/v1/reports/1
```

### 📝 Data Models

**GameDTO:**
```json
{
  "id": 1,
  "name": "Elden Ring",
  "developer": "FromSoftware",
  "genre": "Action RPG",
  "platforms": ["WINDOWS", "PLAYSTATION", "XBOX"],
  "deckVerifiedStatus": true
}
```

**ReportDTO:**
```json
{
  "id": 1,
  "gameId": 1,
  "body": "Performance description",
  "verdict": "PLATINUM",
  "instability": "STABLE",
  "overallRating": "10/10",
  "multiplayerRating": "9/10",
  "distro": "Fedora 40",
  "kernel": "6.9.5-200.fc40.x86_64",
  "ramGb": 32,
  "gpuDriver": "Mesa 24.1.2",
  "gpuModel": "AMD Radeon RX 6600",
  "cpuModel": "AMD Ryzen 5 3600"
}
```

**Enum Values:**
- **Verdict**: `PLATINUM`, `GOLD`, `SILVER`, `BRONZE`, `BORKED`
- **Instability**: `STABLE`, `UNSTABLE`, `VERY_UNSTABLE`
- **Platform**: `WINDOWS`, `LINUX`, `MACOS`, `PLAYSTATION`, `XBOX`, `NINTENDO_SWITCH`, `STEAM_DECK`

## ⚙️ Configuration

### Database Configuration

**PostgreSQL (Default):**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/neutrondb
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**MySQL:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/neutrondb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

**H2 (For Testing):**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

### Application Properties

Additional configuration options:

```properties
# Server port (default: 8080)
server.port=8080

# Logging level
logging.level.com.neutrondb.neutrondb=DEBUG

# JPA settings
spring.jpa.open-in-view=false
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# CORS configuration (already configured in WebConfig)
# See src/main/java/com/neutrondb/neutrondb/config/WebConfig.java
```

## 🔨 Development

### Running Tests

```bash
mvn test
```

### Package the Application

```bash
mvn clean package
```

The JAR file will be created in `target/NeutronDB-0.0.1-SNAPSHOT.jar`

### Running the JAR

```bash
java -jar target/NeutronDB-0.0.1-SNAPSHOT.jar
```

### Hot Reload

Spring Boot DevTools enables automatic restart when code changes. Just save your files and the application will restart automatically.

### Adding Dependencies

Add dependencies to `pom.xml` and run:
```bash
mvn clean install
```

---

**Note:** This is the backend API documentation. For frontend documentation, please refer to the `frontend/` directory.
