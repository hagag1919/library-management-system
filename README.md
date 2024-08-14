
# Library Management System API Documentation

## Overview

This document provides instructions on setting up, running, and interacting with the API endpoints of the Library Management System built using Spring Boot. The system facilitates the management of patrons, books, and borrowing records.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Maven**: For managing dependencies and building the project.
- **Spring Boot**: This project uses Spring Boot, which is included in the project dependencies.

## Setup and Run the Application

### Step 1: Clone the Repository

```bash
git clone <repository-url>
cd <repository-directory>
```

### Step 2: Build the Project

```bash
mvn clean install
```

### Step 3: Run the Application

```bash
mvn spring-boot:run
```

The application will start running on `http://localhost:8080`.

## API Endpoints

### Patron Management

#### 1. Add a New Patron

- **Endpoint**: `/patrons/add`
- **Method**: `POST`
- **Parameters**:
  - `Patron`: JSON object containing patron details.
- **Response**: 
  - `200 OK`: Patron added successfully.
  - `400 Bad Request`: Patron already exists.

#### 2. Update a Patron

- **Endpoint**: `/patrons/update`
- **Method**: `POST`
- **Parameters**:
  - `id` (Long): ID of the patron to update.
  - `Patron`: JSON object with updated patron details.
- **Response**:
  - `200 OK`: Patron updated successfully.
  - `400 Bad Request`: Patron does not exist.

#### 3. Delete a Patron

- **Endpoint**: `/patrons/delete`
- **Method**: `POST`
- **Parameters**:
  - `id` (Long): ID of the patron to delete.
- **Response**:
  - `200 OK`: Patron deleted successfully.
  - `400 Bad Request`: Patron does not exist.

#### 4. Get a Patron by ID

- **Endpoint**: `/patrons/get`
- **Method**: `GET`
- **Parameters**:
  - `id` (Long): ID of the patron.
- **Response**:
  - `200 OK`: Returns the patron details.
  - `404 Not Found`: Patron does not exist.

#### 5. Get All Patrons

- **Endpoint**: `/patrons/getall`
- **Method**: `GET`
- **Response**:
  - `200 OK`: Returns a list of all patrons.

### Book Management

#### 1. Add a New Book

- **Endpoint**: `/books/add`
- **Method**: `POST`
- **Parameters**:
  - `Book`: JSON object containing book details.
- **Response**:
  - `200 OK`: Book added successfully.
  - `400 Bad Request`: Book already exists.

#### 2. Update a Book

- **Endpoint**: `/books/update`
- **Method**: `POST`
- **Parameters**:
  - `id` (Long): ID of the book to update.
  - `Book`: JSON object with updated book details.
- **Response**:
  - `200 OK`: Book updated successfully.
  - `400 Bad Request`: Book does not exist.

#### 3. Delete a Book

- **Endpoint**: `/books/delete`
- **Method**: `POST`
- **Parameters**:
  - `id` (Long): ID of the book to delete.
- **Response**:
  - `200 OK`: Book deleted successfully.
  - `400 Bad Request`: Book does not exist.

#### 4. Get a Book by ID

- **Endpoint**: `/books/get`
- **Method**: `GET`
- **Parameters**:
  - `id` (Long): ID of the book.
- **Response**:
  - `200 OK`: Returns the book details.
  - `404 Not Found`: Book does not exist.

#### 5. Get All Books

- **Endpoint**: `/books/getAll`
- **Method**: `GET`
- **Response**:
  - `200 OK`: Returns a list of all books.

### Borrowing Record Management

#### 1. Borrow a Book

- **Endpoint**: `/borrowing-record/borrow-book`
- **Method**: `POST`
- **Parameters**:
  - `Book`: JSON object containing book details.
  - `Patron`: JSON object containing patron details.
- **Response**:
  - `200 OK`: Book borrowed successfully.
  - `400 Bad Request`: Book is not available.

#### 2. Return a Book

- **Endpoint**: `/borrowing-record/return-book`
- **Method**: `POST`
- **Parameters**:
  - `bookId` (Long): ID of the book to return.
  - `patronId` (Long): ID of the patron returning the book.
- **Response**:
  - `200 OK`: Book returned successfully.
  - `404 Not Found`: Borrowing record not found.

## Conclusion

This documentation provides a comprehensive guide to set up and interact with the Library Management System's API. If you encounter any issues, please refer to the codebase or contact Me.
