# Golf Club Tournament API

This is a Spring Boot REST API to manage golf tournaments, including members, and match data. It supports search functionality via REST endpoints and is containerized using Docker.

---

## 📦 Features

- Manage Members, and Tournaments
- MySQL database support
- RESTful API endpoints
- Search APIs (detailed below)
- Dockerized for easy deployment

---

## 🔎 Supported Search APIs

| Endpoint                                      | Description                                |
|-----------------------------------------------|--------------------------------------------|
| `GET /members/search?name={name}`             | Search members by name                     |
| `GET /members/search?phone={number}`          | Search teams by phone number               |
| `GET /members/search?startDate={date}`        | Search members by tournaments start date   |
| `GET /tournaments/search?startDate={date}`    | Search tournaments by start date           |
| `GET /tournaments/search?location={location}` | Search tournaments by location             |
| `GET /tournaments/{id}/members`               | Search tournaments by id with all members  |
> 📘 Note: All endpoints follow standard Spring Data REST conventions or custom controller mappings.

---

## 🐳 Run with Docker

### 📁 Prerequisites

- Docker installed
- Docker Compose (`docker-compose.yml`)
- MySQL RDS or local instance running (update `application.properties` accordingly if for AWS)

---

### 🛠️ Step-by-Step

#### 1. Clone the repo
bash
git clone https://github.com/mimya2008/golf_club_tournament.git
cd golf_club_tournament

#### 2. Build the JAR with Maven
mvn clean package

#### 3. Build Docker image
docker build -t golf-club-tournament .

#### 4. Run Docker container
docker compose up

If using MySQL RDS, make sure application.properties points to it:
spring.datasource.url=jdbc:mysql://golf-club-tour-instance-1.cj3urig8rllm.us-east-1.rds.amazonaws.com/golf_club
spring.datasource.username=admin
spring.datasource.password=password

For questions or contributions, feel free to open an issue or PR.
