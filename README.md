# Online Car Rental Project

## Project Overview

The Online Car Rental Project is an innovative system designed to streamline and automate the vehicle rental process. It offers a user-friendly interface for clients to book cars, comprehensive data validation, and robust integration with backend databases. The system is built with a multi-layered architecture to clearly separate business logic, data handling, and user interactions, resulting in a maintainable and scalable platform.

---

## Key Goals

- **Efficiency:** Simplify and automate the car rental process to reduce manual intervention.
- **Reliability:** Ensure accurate data validation and secure data storage.
- **User Experience:** Provide an intuitive, responsive interface for both customers and system managers.

---

## Advantages

- **Robust Data Management:** Utilizes well-defined models (entities) and repositories for efficient data storage and retrieval.
- **Clear Separation of Concerns:** Distinct layers for controllers, services, and GUI reduce complexity and enhance maintainability.
- **Enhanced Communication:** Built-in data validation and detailed error reporting ensure a secure and user-friendly experience.

---

## Project Structure

The project is organized into the following layers:

### 1. Models (Entities)
These classes represent the core data objects:
- **Client:**  
  - *Attributes:* name, identity number, driver’s license number, credit card number, email, phone  
  - *Responsibility:* Stores client information.
  
- **Car:**  
  - *Attributes:* model, make, year, daily price, availability  
  - *Responsibility:* Maintains vehicle details.

- **Rental:**  
  - *Attributes:* client, car, rental dates, pick-up/delivery location and time  
  - *Responsibility:* Manages rental booking information.

### 2. Controllers
Controllers handle requests from the user interface and pass data to the services:
- **ClientController:** Manages the creation and validation of client data.
- **CarController:** Facilitates displaying available cars and booking a car.
- **RentalController:** Oversees the creation, modification, and update of rental records.
- **GUIController:** Handles the display of input forms and error messages.

### 3. Services
Services encapsulate the business logic:
- **ClientService:** Processes logic to create and manage client records.
- **CarService:** Manages the search, booking process, and administration of vehicle data.
- **RentalService:** Controls the creation and management of rental bookings.
- **ValidationService:** Validates client, car, and rental data before further processing.

### 4. Repositories
Repositories interface with the database:
- **ClientRepository:** Handles the saving and retrieval of client data.
- **CarRepository:** Manages vehicle data storage and retrieval.
- **RentalRepository:** Oversees the storage and fetching of rental records.

### 5. Graphical User Interface (GUI)
The GUI component provides a simple, responsive interface:
- **ClientForm:** Collects and validates client data input.
- **CarSelectionForm:** Presents a list of available vehicles for booking.
- **RentalSummaryForm:** Displays an overview of the rental order.
- **ConfirmationForm:** Confirms the rental booking.
- **ErrorForm:** Shows error messages for incorrect or incomplete data input.

### 6. Database
The system uses a backend database to store:
- **ClientDatabase:** Client details.
- **CarDatabase:** Car inventory information.
- **RentalDatabase:** Rental booking records.

### 7. System Operation Flow

1. **Data Input:**  
   The client enters necessary details (e.g., name, identification, driver’s license, credit card information) and selects a vehicle along with rental dates via a GUI form.
   
2. **Data Validation:**  
   The ValidationService verifies the entered data meets required criteria.
   
3. **Booking Process:**  
   The CarService checks vehicle availability; if available, the car is reserved.
   
4. **Rental Creation:**  
   The RentalService creates a rental record, stored in the database.
   
5. **Confirmation & Notification:**  
   A confirmation screen is displayed to the client, and complete rental details are sent to the manager for further processing.

---
