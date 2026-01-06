## Twitter-like REST API

This is a simple REST API built with Spring Boot.  
It uses JWT for authentication and supports role-based access (ADMIN/BASIC).  
You can create tweets, check a paginated feed, and manage users in a secure way.  
The backend is organized and easy to follow, with a focus on clean code and practical security.


⚠️**Note**: Since the app is hosted on Render's free plan, it may take a few minutes to start.

username: ADMIN
password: 1352

[simple-tweet.onrender.com](https://simple-tweet.onrender.com/swagger-ui/index.html#/)

---

## Spring Security JWT API

- A RESTful API built with Spring Boot
- Focused on authentication, authorization, and clean backend design
- Demonstrates JWT authentication and role-based access control
- Built with a real-world backend structure in mind

---

## Features

- JWT authentication using RSA public and private keys
- Role-based authorization (ADMIN / BASIC)
- User registration and login
- Tweet creation, deletion, and paginated feed
- Admin-only access to list users
- Stateless security (no HTTP sessions)
- Clean, domain-oriented architecture
- DTO-based API responses (entities are not exposed)

---

## Project Structure

- auth
    - Authentication and login flow
- user
    - User management
- tweet
    - Tweet domain (feed, create, delete)
- role
    - Roles and permissions
- config
    - Application and security configuration

Each domain contains:
- controller – HTTP layer
- service – business logic
- entity – persistence models
- repository – database access
- dto – request and response objects

---

## Authentication and Authorization

- Authentication endpoint: /login
- Protected endpoints require a valid JWT
- Tokens are signed using RSA keys
- Authorization is enforced using:
    - JWT scopes
    - Method-level security with @PreAuthorize

---

## Access Rules

- ADMIN
    - List all users
    - Delete any tweet

- BASIC
    - Create tweets
    - Delete only their own tweets

---

## Tweet Feed

- Paginated feed endpoint
- Sorted by creation date (newest first)
- Lightweight response objects
- Simple and efficient design

---

## Initial Admin Setup

- On application startup:
    - The system checks if an ADMIN user exists
    - If not, one is created automatically
- Guarantees admin access in any environment

---

## Design Principles

- Controllers contain no business logic
- Services encapsulate all domain rules
- Entities are never exposed directly
- Clear and explicit code style
- Strong focus on security and maintainability

---

## Purpose

- Demonstrates a real-world Spring Security setup
- Shows JWT-based authentication and authorization
- Applies clean backend architecture principles
- Suitable as a portfolio project or backend foundation
