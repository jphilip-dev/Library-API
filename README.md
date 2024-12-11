# Library Management REST API

This is a simple Library Management System REST API built using Spring Boot. It allows users and administrators to interact with books and users through different endpoints.

## Features

- **User Registration**: Users can register and await approval by the administrator.
- **Book Management**: Administrators can add, update, and delete books.
                       while Users(Role) is limited to view only
- **User Management**: Administrators can view and update user details.
- **Access Control**: Certain endpoints are only accessible to authenticated administrators.

## API Endpoints

### Public Endpoints

- **GET** `/api`  
  - Message: "Hello! You're in the landing End point of the Library API!"
  
- **POST** `/api/register`  
  - Register a new user (unauthenticated access only).

### Book Management (Admin)

- **GET** `/api/books`  
  - Get a list of all books.
  
- **GET** `/api/books/{bookId}`  
  - Get a specific book by ID.
  
- **POST** `/api/books`  
  - Add a new book (administrator only).
  
- **PUT** `/api/books`  
  - Update an existing book (administrator only).
  
- **DELETE** `/api/books/{bookId}`  
  - Delete a book by ID (administrator only).

### User Management (Admin)

- **GET** `/api/users`  
  - Get a list of all users (administrator only).
  
- **PUT** `/api/users`  
  - Update a user's details (administrator only).
  
- **GET** `/api/users/{userId}`  
  - Get a specific user by ID (administrator only).

### Access Denied

- **GET** `/api/access-denied`  
  - Returns an unauthorized access message.

## Database Schema

SQL scripts for creating the database tables are as follows:

```sql
-- Table creation for api_roles
CREATE TABLE `api_roles` (
  `user_id` varchar(64) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx` (`user_id`,`role`),
  CONSTRAINT `fk_user_roles` FOREIGN KEY (`user_id`) REFERENCES `api_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table creation for api_users
CREATE TABLE `api_users` (
  `user_id` varchar(64) NOT NULL,
  `password` varchar(80) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table creation for book
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `published_date` date NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `genre` int NOT NULL,
  `copies_available` int DEFAULT '0',
  `total_copies` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `genre` (`genre`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`genre`) REFERENCES `genre` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
```

## Technologies Used
- **Spring Boot**: For building the REST API.
- **JPA**: For data persistence.
- **MySQL**: Database management.
- **Spring Security**: For authentication and authorization.

## How to Run
- Clone the repository:
git clone https://github.com/your-username/library-api.git

- Set up the MySQL database and configure the application.properties with your database credentials.

- Run the Spring Boot application:
mvn spring-boot:run

- Access the API at:
http://localhost:8080/api
