# Heading
Tech Support Ticketing System

A full-stack web application for managing customer tech support tickets, built as a Software Engineering capstone project. Technicians can log in to view, search, update, and resolve support requests submitted by customers, with urgent requests flagged and tracked separately.

##
# Heading
Live demo: https://d424-software-engineering-capstone-fgqn.onrender.com

##
# Heading
Features
Public ticket submission — customers submit a support request (name, phone, email, device type, issue description) without needing an account, with server-side validation on all fields
Urgent request handling — requests can be flagged as urgent, which routes them into a dedicated request type for faster triage
Technician authentication — Spring Security with BCrypt-hashed credentials and form-based login/logout; only authenticated technicians can view, search, or manage tickets
Ticket management — technicians can search requests by keyword, update ticket status (Open / In Progress / Closed), and delete resolved tickets
Reporting dashboard — aggregate view of ticket volume by status and device type, most common device type, most recent request, requests created today, and total urgent request count

##
# Heading
Tech Stack
Backend: Java 17, Spring Boot 3.4.5, Spring Data JPA, Spring Security
Frontend: Thymeleaf, HTML/CSS
Database: PostgreSQL
Testing: JUnit, Mockito, MockMvc, Spring Security Test
Deployment: Docker, Render

##
# Heading
Getting Started
Prerequisites
Java 17
Maven (or use the included mvnw wrapper)
PostgreSQL (local instance, or a connection string from a hosted provider)

##
# Heading
Local Setup
Clone the repo:
git clone https://github.com/LukeModo5/Software-Engineering-Capstone.git
cd Software-Engineering-Capstone
Create a local PostgreSQL database and update src/main/resources/application-local.properties with your credentials, or set the following environment variables:
DATABASE_URL=jdbc:postgresql://localhost:5432/<your_db_name>
DATABASE_USERNAME=<your_username>
DATABASE_PASSWORD=<your_password>

##
# Heading
Run the app:
./mvnw spring-boot:run
Visit http://localhost:8080 to submit a request, or http://localhost:8080/login to sign in as a technician.
Running with Docker
docker build -t techsupport-crud .
docker run -p 8080:8080 \
-e DATABASE_URL=jdbc:postgresql://<host>:5432/<db> \
-e DATABASE_USERNAME=<username> \
-e DATABASE_PASSWORD=<password> \
techsupport-crud
Running Tests
./mvnw test
Project Structure
src/main/java/com/techsupport/crud/techsupportCRUD/
├── config/       Spring Security configuration
├── controller/   Request routing and page controllers
├── model/        SupportRequest, UrgentSupportRequest, TechnicianAccount
├── repo/         Spring Data JPA repositories
└── service/      Business logic for requests and technician accounts

##
# Heading
To login as a technician use these credentials
Username: Admin
Password: password

##
# Heading
Author
Luke Modory — github.com/LukeModo5


