# Online Car Rental Project

## About this project

This JavaFX desktop application was developed as part of a joint training project during the CyberProAI x ITWorks bootcamp (2024–2025), in collaboration with GitHub user [@Johntarakay](https://github.com/Johntarakay).

### Shared project responsibilities included:
- Designing the application structure using the MVC architectural pattern
- Creating a user-friendly interface with JavaFX and FXML
- Setting up project management via Maven and Git
- Implementing input validation and object serialization for data persistence

My primary focus during the collaborative stage included GUI development, controller logic, and integration of front-end with back-end components.

---

Since completing the program, I (Anat Ignatovich) have continued working on this project independently to:
- Extend functionality (e.g., user flows, data validation layers)
- Refactor and optimize the existing codebase
- Improve UX and modularity
- Introduce a database layer (planned)
- Package the application into a standalone executable format

This project now reflects my continued personal growth as a junior Java developer.


## Project Overview
**Online Car Rental Project** is a system designed to streamline and automate the car rental process.  
It offers:
- A user-friendly interface for customers to book cars
- Comprehensive data validation
- Manager tools to process orders and manage records
- Admin interface for direct data adjustments

The system is built with a **multi-tier architecture** to separate business logic, data handling, and user interaction — ensuring maintainability and scalability.

---

## Main Goals
- **Efficiency**: Simplify and automate workflows
- **Reliability**: Accurate validation and secure storage
- **User Experience**: Smooth UI for both customers and staff

---

## Benefits
- **Reliable data management**: Clear entity models and repository logic
- **Separation of concerns**: GUI, services, and business logic are decoupled
- **Advanced communication**: Data validation and meaningful error feedback

---

## Project Structure

1. **Models (Entities)** – represent core data objects  
2. **Controllers** – handle user actions and UI events  
3. **Services** – contain business logic and processing rules  
4. **Repositories** – manage data persistence  
5. **Graphical User Interface (GUI)** – JavaFX-based user interface  
6. **Error Classes** – custom exceptions for validation feedback  
7. **Database** – internal file-based persistence (serialization)  
8. **Logging System** – outputs activity to an accessible log file  

---

## Data Flow Overview

### Data Entry
Customer selects a car and fills in personal/rental data:  
*Name, ID, Driver's License, Credit Card, etc.*

### Data Validation
`ValidationService` checks all inputs for format and business rules.

### Booking Process
Car availability is verified. If free, the reservation is processed.

### Rental Creation
Rental data is bundled into a `Rental` object and saved.

### Confirmation & Notification
Customer receives confirmation screen.  
Manager gets access to the booking for further handling.

### Access to Orders
Users can log in and view their orders using credentials or authorization flow.

---

This README describes the functional structure and goals of the Online Car Rental project, currently maintained and expanded by Anat Ignatovich.
