# Employee Management App

Java 20 • Apache Tapestry • Spring • Hibernate • PostgreSQL

## Features
- Login page (username/password) with validation
- Employee list using Tapestry Grid
- View individual employee details
- Create/Edit employee (server + JS validation for age)
- Roles/permissions (Admin full, Employee limited)
- Reusable header component (company info) on all pages

## Tech Stack
- **Frontend:** Apache Tapestry
- **Service Layer:** Spring
- **DAO Layer:** Hibernate (Criteria API)
- **DB:** PostgreSQL (pgAdmin for client)

## Build command
- mvn clean package jetty:run
