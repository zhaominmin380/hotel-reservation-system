package model;

import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        try{
            Customer customer = new Customer("first", "second", "email@email.com");
            Room room = new Room("100", 135.0, RoomType.Single);
            Date checkInDate = new Date(2024-1900, 12, 20);
            Date checkOutDate = new Date(2024-1900, 12, 29);
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            System.out.println(reservation);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
