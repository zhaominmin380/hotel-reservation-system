# 🏨 Hotel Reservation System

This is a **Java-based hotel reservation system** built using Object-Oriented Programming principles. It provides a command-line interface (CLI) for customers and administrators to manage room reservations, availability, and customer information.

---

## ✅ Features

- 📅 Book and manage hotel room reservations
- 🧾 View reservation history by customer email
- 🧍 Add new customers and retrieve customer info
- 🏨 Admin functions:
  - View all rooms and reservations
  - Add new rooms
- 🧪 Modular design with clear separation of `model`, `service`, `api`, and `ui` layers

---

## 📂 Project Structure

```
src/
├── api/                  # Interfaces for accessing resources
│   ├── AdminResource.java
│   ├── HotelResource.java
│   └── Tester.java
│
├── model/                # Core data classes and interfaces
│   ├── Customer.java
│   ├── FreeRoom.java
│   ├── IRoom.java
│   ├── Reservation.java
│   ├── Room.java
│   ├── RoomType.java
│   └── Tester.java
│
├── service/              # Business logic layer
│   ├── CustomerService.java
│   ├── ReservationService.java
│   └── Tester.java
│
└── ui/                   # Command-line UI interface
    ├── AdminMenu.java
    ├── HotelApplication.java
    └── MainMenu.java
```

---

## 🚀 How to Run

### 🛠 Requirements

- Java 8 or above
- IntelliJ / Eclipse or any Java IDE (optional)

### ▶️ Steps

1. Clone this repository:
   ```bash
   git clone https://github.com/zhaominmin380/hotel-reservation-system.git
   cd hotel-reservation-system
   ```

2. Compile and run `HotelApplication.java`:
   ```bash
   javac src/ui/HotelApplication.java
   java src.ui.HotelApplication
   ```

3. Follow the menu prompts to use the system.

---

## ✨ Object-Oriented Design Principles Used

- Encapsulation (via getters/setters)
- Abstraction (interfaces like `IRoom`)
- Inheritance (`Room` and `FreeRoom`)
- Separation of concerns (divided by `model`, `service`, `ui`, `api`)

---

## 🙌 Authors

- This project was developed as part of a course assignment to practice OOP design patterns and Java CLI development.
