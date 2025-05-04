# Store Sales System

A point-of-sale system designed to handle sales transactions for a retail store. This application manages inventory, processes sales, calculates taxes, and generates receipts.

## Project Overview

This system was developed as part of the Object-Oriented Design course at KTH Royal Institute of Technology. It implements a layered architecture with clear separation of concerns between the view, controller, model, and integration layers.

## Features

- Process sale transactions
- Item identification and pricing
- Tax calculation
- Discount handling
- Receipt generation
- Inventory management
- Sales logging

## Project Structure

- `src/main/java/se/kth/iv1350/storesalessystem/` - Source code
  - `controller/` - Application controller
  - `model/` - Business logic and domain objects
  - `view/` - User interface
  - `integration/` - External system integration
  - `dto/` - Data transfer objects
  - `util/` - Utility classes
- `test/` - Test cases

## Setup and Installation

### JDK

- Java Development Kit (JDK) 21 or higher

### Running the Application

1. Clone the repository
2. Open the project in your IDE
3. Run the `Main` class in the `se.kth.iv1350.storesalessystem.startup` package

## Testing

The project includes JUnit 5 tests for verifying the functionality of different components.

## Design Patterns

This project implements several design patterns:
- MVC (Model-View-Controller)
- Observer pattern for sale events
- DTO (Data Transfer Object) pattern
- Facade pattern in the controller

## Author

Rafael Fernandes
