## 🚀 Run with Docker (Pull from Docker Hub)

### 1️⃣ Create Docker Network

docker network create app-network

### 2️⃣ Run MySQL Container

docker run --name mysqldb \
--network app-network \
-e MYSQL_ROOT_PASSWORD=Vaib@0088 \
-e MYSQL_DATABASE=employeedb \
-d mysql:8

### 3️⃣ Run User Management App

docker run --name usermanagement \
--network app-network \
-p 8080:8080 \
-e SPRING_PROFILES_ACTIVE=docker \
vaib0088/usermanagement:1.0

Application will be available at:
http://localhost:8080

----------------------------------------------------------------------------------------------------------------

## 🚀 Run Full Application Using Docker Compose

git clone https://github.com/Vaib0088/user-management-app.git
cd user-management-app
docker-compose up --build

Application will start at:
http://localhost:8080

----------------------------------------------------------------------------------------------------------------

## Docker Image
https://hub.docker.com/r/vaib0088/usermanagement

----------------------------------------------------------------------------------------------------------------

Spring Boot connects to MySQL using Docker service name (mysqldb) inside Docker network.
