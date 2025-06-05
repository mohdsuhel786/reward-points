# reward-points
Spring Boot Reward Points Application

Overview

This is a Spring Boot application that manages customer transactions and calculates reward points based on spending. The app exposes RESTful APIs to create transactions and retrieve reward summaries per customer.


---

Features

Record customer transactions (customer ID, name, amount, transaction Date).

Calculate reward points based on transaction amounts.

Retrieve rewards summary for individual customers.

Use in-memory H2 database by default for easy testing.

Well-structured service and repository layers.

Unit and integration tests with JUnit 5 and Mockito.



---

Reward Points Rules

1 point for every dollar spent over $50.

2 points for every dollar spent over $100.


Example:

Transaction amount: $120
Points = (50 * 0) + (50 * 1) + (20 * 2) = 90 points


---

Technologies Used

Java 17+

Spring Boot 3.4.6

Spring Data JPA

H2 Database (default, can configure MySQL)

Maven

JUnit 5 + Mockito for testing

Lombok (optional for boilerplate reduction)



---

Getting Started

Prerequisites

Java JDK 17 or higher

Maven 3.8+

IDE (IntelliJ, Eclipse, VSCode)

Git (for cloning repo)


Clone the repository

git clone https://github.com/mohdsuhel786/reward-points.git
cd cd reward-points

Build and Run

mvn clean install
mvn spring-boot:run

The application will start on http://localhost:8080


---

API Endpoints

Method	Endpoint	Description

GET	/rewards	Get List of total reward points for each customer  (http://localhost:8080/rewards)

---
Sample Transaction JSON

[

{

"customerId": 1,

"customerName": "John Wick",

"monthlyRewards": {

"2025-03": 90,

"2025-05": 5,

"2025-04": 25

},

"totalRewards": 120

"customerId": 2,

"customerName": "Allen Will",

"monthlyRewards": {

"2025-05": 40,

"2025-04": 110

},

"totalRewards": 150

},

{

"customerId": 3,

"customerName": "Ramesh Kumar",

"monthlyRewards": {

"2025-05": 0,

"2025-04": 70

},

"totalRewards": 70

}

---

Running Tests

Run unit and integration tests using Maven:

mvn test


---

Project Structure

controller — REST controllers

service — Business logic and reward calculation

repository — Spring Data JPA repositories

model — Entity and DTO classes

dto — Data transfer objects

exception — Custom exception handling
