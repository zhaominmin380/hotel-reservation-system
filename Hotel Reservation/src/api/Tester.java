package api;
import java.util.*;
import model.*;
import service.ReservationService;

public class Tester {
    public static void main(String[] args) {
        HotelResource hotelResource = HotelResource.getInstance();

        // 創建客戶
        hotelResource.createACustomer("john.doe@example.com", "John", "Doe");

        // 添加房間
        IRoom room101 = new Room("101", 100.0, RoomType.Single);
        ReservationService.getInstance().addRoom(room101);

        // 查找房間
        IRoom foundRoom = hotelResource.getRoom("101");
        System.out.println("Found Room: " + foundRoom);

        // 預訂房間
        Date checkIn = new Date(2024, 1, 1);
        Date checkOut = new Date(2024, 1, 5);
        Reservation reservation = hotelResource.bookARoom("john.doe@example.com", foundRoom, checkIn, checkOut);
        System.out.println("Reservation: " + reservation);

        // 查詢客戶的預訂
        System.out.println("Customer Reservations:");
        hotelResource.getCustomersReservations("john.doe@example.com").forEach(System.out::println);

        // 查找可用房間
        System.out.println("Available Rooms:");
        hotelResource.findARoom(new Date(2024, 1, 3), new Date(2024, 1, 7)).forEach(System.out::println);
    }
}
