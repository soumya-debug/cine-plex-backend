# NMS Cinemas - Movie Ticket Booking System

## Background of the Project

**NMS Cinemas** is a popular movie theater chain established in 2004 in Pune, India. Over the years, NMS Cinemas has become known for its wide variety of movies at affordable prices. However, since 2010, the company has seen a significant decline in sales, primarily due to the rise of digital platforms like **BookMyShow** and **Paytm**, which allow users to book tickets online, bypassing the need for intermediaries.

To counter this trend, NMS Cinemas decided to modernize their business model by developing an easy-to-use and visually appealing website that enables users to book movie tickets online. They hired a developer to create this platform, ensuring it aligns with the business needs and works seamlessly across devices. The project’s main goal is to provide a smooth, user-friendly interface for booking tickets while integrating backend services to manage movies, showtimes, and bookings.

---

## Project Overview

This project represents an **Online Movie Ticket Booking System** for **NMS Cinemas** built using **Spring Boot** for the backend and **Angular** for the frontend. The system allows users to browse movies, book tickets, and make secure payments—all within a modern, responsive web interface.

### Key Features:
- **User Registration & Authentication**: Secure login and registration using JWT (JSON Web Tokens).
- **Movie Listings**: Display available movies with details like name, description, and showtimes.
- **Movie Booking**: Users can select movies, pick showtimes, and book tickets online.
- **Admin Panel**: Admins can manage movie listings, showtimes, and bookings.
- **Payment Integration**: Integration with a third-party payment gateway (e.g., Razorpay or Paytm).
- **Responsive Design**: Fully responsive UI optimized for desktop and mobile devices.

---

## Tech Stack

This project uses the following technologies:

- **Backend**: 
  - Spring Boot (Java) for RESTful API development
  - JWT for user authentication and authorization
  - MySQL for database management

- **Frontend**: 
  - Angular for the user interface
  - HTML5, CSS3, and JavaScript for frontend development

- **Build Tool**:
  - Maven for dependency management and project building

- **Payment Gateway**:
  - Integration with **Razorpay** or **Paytm** for secure payment processing

- **Deployment**:
  - Docker for containerization
  - NGINX as a reverse proxy for frontend and backend
  - Cloud-based deployment on platforms such as AWS or DigitalOcean

---

## Project Structure

This project follows a modular structure for both frontend and backend. Below is an overview of the directories and files in the project.

### Backend (Spring Boot)

```bash
src/
 ├── main/
 │   ├── java/com/nms/cinemas/
 │   │   ├── controller/            # RESTful API controllers
 │   │   ├── service/               # Business logic services
 │   │   ├── repository/            # Database repositories using Spring Data JPA
 │   │   ├── model/                 # Entity classes
 │   │   ├── dto/                   # Data Transfer Objects (DTOs)
 │   │   └── security/              # Swagger & JWT authentication and security configuration
 │   └── resources/
 │       ├── application.properties # Database and server configuration
 │       ├── static/                # Static resources (CSS, JS, images)
 │       └── templates/             # Server-side templates (if used with Thymeleaf)
 └── test/
     └── java/com/nms/cinemas/      # Unit and integration tests

src/
 ├── app/
 │   ├── components/              # Angular components (e.g., movie listings, booking forms)
 │   ├── services/                # Services for API communication
 │   ├── models/                  # TypeScript models representing backend DTOs
 │   ├── routing/                 # Routing configurations
 │   └── state/                   # State management (if using NGRX or services)
 └── assets/
     └── images/                  # Image assets (e.g., movie posters, logos)

Installation and Setup
To run the project locally, follow these steps:

Prerequisites
Make sure the following tools are installed:

JDK 11 or higher

Maven for backend

Node.js and npm for frontend

MySQL (or another compatible relational database)

Backend Setup
Clone the repository:

bash
Copy
Edit
git clone https://github.com/nmscinemas/movie-booking-system.git
cd movie-booking-system
Set up the MySQL database:

Create a new MySQL database named nms_cinemas.

Update the application.properties file with your MySQL connection details:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/nms_cinemas
spring.datasource.username=root
spring.datasource.password=your_password
Build the backend:

bash
Copy
Edit
mvn clean install
Run the backend:

bash
Copy
Edit
mvn spring-boot:run
The backend will be running on http://localhost:8080.

Frontend Setup
Navigate to the frontend directory:

bash
Copy
Edit
cd frontend
Install the necessary dependencies:

bash
Copy
Edit
npm install
Start the Angular development server:

bash
Copy
Edit
ng serve
The frontend will be running on http://localhost:4200.

API Documentation
The backend exposes several RESTful APIs for user authentication, movie management, and ticket booking.

Authentication
POST /api/auth/register: Register a new user

POST /api/auth/login: Log in and receive a JWT token

Movie Management
GET /api/movies: List all available movies

GET /api/movies/{id}: Get details of a specific movie

POST /api/movies: Add a new movie (Admin only)

PUT /api/movies/{id}: Update movie details (Admin only)

DELETE /api/movies/{id}: Delete a movie (Admin only)

Booking
GET /api/bookings: List all bookings

POST /api/bookings: Create a new booking

GET /api/bookings/{id}: Get booking details by ID

DELETE /api/bookings/{id}: Cancel a booking

Contribution Guidelines
We welcome contributions to the NMS Cinemas Movie Ticket Booking System!

How to Contribute
Fork the repository.

Clone your fork:

bash
Copy
Edit
git clone https://github.com/yourusername/movie-booking-system.git
Create a new branch for your feature or bugfix:

bash
Copy
Edit
git checkout -b feature/my-new-feature
Make your changes.

Commit your changes:

bash
Copy
Edit
git commit -m "Add a new feature"
Push your changes:

bash
Copy
Edit
git push origin feature/my-new-feature
Create a pull request for review.

License
This project is licensed under the MIT License - see the LICENSE file for details.

Contact
For any issues or inquiries, please contact us at support@nmscinemas.com.

This README provides an in-depth guide to understanding, setting up, and contributing to the NMS Cinemas Movie Ticket Booking System. It includes detailed installation instructions, project structure, API documentation, and contribution guidelines to assist developers in getting started and contributing effectively.
