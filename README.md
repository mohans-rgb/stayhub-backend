StayHub — Hotel Booking Backend

A production-ready RESTful backend for a hotel booking platform, built with Spring Boot 4.1 and Java 21. StayHub handles the full guest lifecycle — from hotel discovery and room availability to payment processing and booking management — with role-based access control and dynamic pricing baked in.


Features


JWT Authentication & Role-Based Authorization — Stateless security with Spring Security; separate access tiers for guests (GUEST) and hotel managers (HOTEL_MANAGER)
Dynamic Pricing Engine — Decorator-pattern pricing pipeline combining Base, Surge, Occupancy, Urgency, and Holiday strategies to compute real-time room prices
Real-Time Inventory Management — Pessimistic locking (SELECT ... FOR UPDATE) on inventory rows prevents double-booking under concurrent load
Stripe Payment Integration — Checkout session initiation, webhook event processing, and automatic refunds on cancellation
Hotel Search & Filtering — Paginated hotel discovery by city, date range, and room availability using optimized JPQL queries
Guest Management — Track and associate guests with bookings (supports multiple guests per reservation)
Global Exception Handling — Centralized @RestControllerAdvice with structured ApiError / ApiResponse wrappers
OpenAPI / Swagger UI — Auto-generated API docs via SpringDoc



Tech Stack

LayerTechnologyLanguageJava 21FrameworkSpring Boot 4.1SecuritySpring Security + JWT (JJWT 0.12.6)PersistenceSpring Data JPA + PostgreSQLPaymentsStripe Java SDK 33.1MappingModelMapper 3.2.6BoilerplateLombokAPI DocsSpringDoc OpenAPI (Swagger UI)BuildMaven



