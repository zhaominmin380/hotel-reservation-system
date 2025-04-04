package service;

import model.*;

import java.util.*;
public class Tester {
    public static void main(String[] args) {
        ReservationService service = ReservationService.getInstance();

        // 創建房間

        IRoom room1 = new Room("101", 100.0, RoomType.Single);
        IRoom room2 = new Room("102", 0.0, RoomType.Double);

        // 添加房間
        service.addRoom(room1);
        service.addRoom(room2);

        // 查找房間
        System.out.println("Room 101: " + service.getARoom("101"));

        // 預定房間
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        Customer customer2 = new Customer("Jane", "Doe", "jane.doe@example.com");
        Reservation reservation = service.reserveARoom(customer, room1, new Date(), new Date());
        Reservation reservation2 = service.reserveARoom(customer2, room2, new Date(), new Date());
        Collection<Reservation> reservations = service.getCustomersReservations(customer2);


        // 打印所有預定
        //service.printAllReservations();
        System.out.println(reservations);
    }
}
