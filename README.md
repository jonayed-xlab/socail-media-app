# Social Media Microservice

## Introduction
This microservice is designed to manage user-related activities for a social media platform.
It handles user information and generates events for user creation, updating, and deletion. 
These events are send to Kafka in real-time for analytics. 
The microservice is using Docker compose file for kafka & Redis config 
and utilizes a relational database (H2).

## Technology Stack
- Spring Boot 3.2.3
- H2 Database
- Kafka
- Redis
- Java 21
- Spring Data JPA
- DevTool
- Docker Compose

## Project Setup
1. Clone the project:
   ```bash
   git clone https://github.com/jonayed-xlab/social-media-app.git
2. Project Branch:
  ```bash
   git checkout socialmedia-server-v1.0.0
```

## API Usage
1. Save User
```
curl --location 'localhost:8080/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "dob": "1990-01-01"
}'
```

2. Update User
```
curl --location --request PUT 'localhost:8080/v1/users/1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "Jonayed",
  "lastName": "Baperi",
  "email": "jonayed.baperi@gmail.com",
  "dob": "2000-01-09"
}'
```

3. Get User by UserID
```
curl --location 'localhost:8080/v1/users/1' \
--data ''

```

4. Get All Users
```
curl --location 'localhost:8080/v1/users' \
--data ''

```

##Docker Commands
1.Check Running Containers
```
sudo docker ps
```
2.Start Docker Compose Services
```
sudo docker-compose up -d
```
3.View Zookeeper Logs

```
sudo docker-compose logs zookeeper
```
4.View Kafka Logs

```
sudo docker-compose logs kafka

```


