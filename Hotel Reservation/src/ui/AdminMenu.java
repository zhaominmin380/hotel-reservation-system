package ui;
import java.util.*;
import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void displayAdminMenu() {
        String userInput;

        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.println("Please select number of the menu option");

            userInput = scanner.nextLine().trim();

            switch (userInput) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Error: Invalid Input");
            }
        } while (!userInput.equals("5"));
    }

    private static void seeAllCustomers() {
        AdminResource.getInstance().getAllCustomers().forEach(System.out::println);
    }
    private static void seeAllRooms() {
        AdminResource.getInstance().getAllRooms().forEach(System.out::println);
    }
    private static void seeAllReservations() {
        AdminResource.getInstance().displayAllReservations();
    }
    private static void addRoom() {
        try {
            while(true) {
                System.out.println("Enter room number");
                String roomNumber = scanner.nextLine();
                System.out.println("Enter price per night");
                double pricePerNight = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                int roomTypeInput;
                while(true) {
                    roomTypeInput = Integer.parseInt(scanner.nextLine());
                    if(roomTypeInput == 1 || roomTypeInput == 2) {
                        break;
                    }
                    else{
                        System.out.println("Enter 1 (Single Bed) or 2 (Double Bed)");
                    }
                }

                RoomType roomType = roomTypeInput == 1 ? RoomType.Single : RoomType.Double;
                Room room = new Room(roomNumber, pricePerNight, roomType);
                List<IRoom> rooms = new ArrayList<>();
                rooms.add(room);
                AdminResource.getInstance().addRoom(rooms);
                System.out.println("Would you like to add another room? (y/n)");
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
            }
        }catch (IllegalArgumentException ex) {
            System.out.println("Error: Invalid Input");
        }
    }

}
