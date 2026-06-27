# StayHub 

StayHub is a backend system for a hotel booking platform inspired by Airbnb.  
It is built using **Spring Boot** and includes authentication, booking management, dynamic pricing, and payment integration.

---



<img width="832" height="863" alt="image" src="https://github.com/user-attachments/assets/d6d0e75d-b4ae-4947-984f-306469a40e06" />


##  Features

###  Authentication & Security
- JWT-based authentication
- Role-based access control (User / Admin)
- Secure endpoints using Spring Security

###  Hotel Management
- Add / update / manage hotels
- Room management system
- Inventory tracking per room

###  Hotel Search & Booking
- Search hotels by filters
- Room availability checking
- Booking creation and status tracking

###  Payment Integration
- Stripe payment gateway integration
- Checkout session handling
- Webhook support for payment confirmation

###  Dynamic Pricing Engine
Implements Strategy Design Pattern:
- Surge pricing
- Holiday pricing
- Occupancy-based pricing
- Urgency-based pricing

###  System Design Concepts
- Clean layered architecture (Controller → Service → Repository)
- DTO-based communication
- Strategy Design Pattern for pricing logic

---

##  Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Stripe API
- JWT
- Maven

---



