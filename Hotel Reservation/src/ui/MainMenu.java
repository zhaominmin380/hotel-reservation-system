package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import api.HotelResource;
import model.IRoom;
import model.Reservation;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void displayMainMenu(){
        String userInput;

        do{
            System.out.println("Welcome to the Hotel Reservation Application:\n");
            System.out.println("-----------------------------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("-----------------------------------------------");
            System.out.println("Please select a number for the menu option");

            userInput = scanner.nextLine();
            switch(userInput){
                case "1":
                    findAndReserve();
                    break;
                case "2":
                    seeMyRes();
                    break;
                case "3":
                    createAcct();
                    break;
                case "4":
                    AdminMenu.displayAdminMenu();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: Invalid Input");
            }
        } while (true);
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use mm/dd/yyyy.");
            return null;
        }
    }

    private static void findAndReserve(){
        try {
            Date checkInDate;
            Date checkOutDate;
            do {
                System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2021");
                String checkIn = scanner.nextLine();
                checkInDate = parseDate(checkIn);
            } while (checkInDate == null);
            do {
                System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2021");
                String checkOut = scanner.nextLine();
                checkOutDate = parseDate(checkOut);
            } while (checkOutDate == null);

            Collection<IRoom> availableRooms = HotelResource.getInstance().findARoom(checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("There are no available rooms for the given dates.");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(checkInDate);
                calendar.add(Calendar.DATE, 7);
                checkInDate = calendar.getTime();

                calendar.setTime(checkOutDate);
                calendar.add(Calendar.DATE, 7);
                checkOutDate = calendar.getTime();
                Collection<IRoom> recommendedRooms = HotelResource.getInstance().findARoom(checkInDate, checkOutDate);
                if (!recommendedRooms.isEmpty()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
                    System.out.println("Recommend Dates: " + formatter.format(checkInDate) + " - " + formatter.format(checkOutDate));
                    for (IRoom room : recommendedRooms) {
                        System.out.println(room);
                    }
                } else {
                    System.out.println("There are no available rooms.");
                    return;
                }
            } else {
                for (IRoom room : availableRooms) {
                    System.out.println(room);
                }
            }
            System.out.println("Would you like to book a room? (y/n)");
            while (true) {
                String isBookRoom = scanner.nextLine();
                if (isBookRoom.equals("y")) {
                    break;
                } else if (isBookRoom.equals("n")) {
                    return;
                } else {
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }
            System.out.println("Do you have an account with us? (y/n)");
            while (true) {
                String hasAccount = scanner.nextLine();
                if (hasAccount.equals("y")) {
                    break;
                } else if (hasAccount.equals("n")) {
                    System.out.println("Create an account first");
                    return;
                } else {
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }
            System.out.println("Enter Email format: name@domain.com");
            String email = scanner.nextLine();
            System.out.println("What room number would you like to reserve?");
            String roomNumber = scanner.nextLine();
            IRoom room = HotelResource.getInstance().getRoom(roomNumber);

            Reservation res = HotelResource.getInstance().bookARoom(email, room, checkInDate, checkOutDate);
            System.out.println(res);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    private static void seeMyRes(){
        try {
            System.out.println("Enter your email");
            String email = scanner.nextLine();
            Collection<Reservation> reservations = HotelResource.getInstance().getCustomersReservations(email);
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    private static void createAcct(){
        try {
            System.out.println("Enter Email format name@domain.com");
            String email = scanner.nextLine();
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();
            HotelResource.getInstance().createACustomer(email, firstName, lastName);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
