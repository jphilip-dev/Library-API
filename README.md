# Library-API

This is a Library REST API built with Spring Boot. It allows users to manage books, loans, and user accounts with roles and authentication using Basic Auth. The API supports functionalities for both users and admins, including registration, login, and management of books and loans.

## Features

- **User Registration & Activation**: Users can register, but their accounts need to be activated by an admin.
- **Book Management**: Admins can add, update, and delete books.
- **Loan Management**: Users can borrow and return books, and admins can manage loans.
- **Basic Authentication**: All API endpoints are protected by Basic Auth.
- **Role-based Access**: The API enforces role-based access for users and admins.
- **Exception Handling**: Custom exceptions for various errors, including validation and loan-related issues.

## Endpoints

### Authentication

- **POST /security/register**: Register a new user (Account needs activation by admin).
- **GET /security/users**: Get a list of all users (Admin only).
- **PUT /security/users/{id}**: Update user details (Admin only).
- **DELETE /security/users/{id}**: Delete a user (Admin only).

### Books

- **GET /api/books**: Get a list of all books (User and Admin).
- **GET /api/books/{bookId}**: Get a book by its ID (User and Admin).
- **POST /api/books**: Add a new book (Admin only).
- **PUT /api/books/{bookId}**: Update a book's details (Admin only).
- **DELETE /api/books/{bookId}**: Delete a book (Admin only).

### Loans

- **GET /api/loans**: Get a list of all loans (User and Admin).
- **GET /api/loans/{id}**: Get details of a loan (User and Admin).
- **POST /api/loans**: Loan a book (User only).
- **PUT /api/loans/return/{id}**: Return a loaned book (User or Admin).
- **DELETE /api/loans/{id}**: Delete a loan (Admin only).

## Authentication

The API uses Basic Authentication for all endpoints. A user must be authenticated to access the API, and roles determine which endpoints they can access. 

- **Admin**: Can manage users, books, and loans.
- **User**: Can view books, borrow books, and view/return loans.

### Example Request

```bash
curl -u username:password http://localhost:8080/api/books
