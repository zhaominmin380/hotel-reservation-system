package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public boolean isOverlap(Date checkIn, Date checkOut) {
        return checkIn.before(checkOutDate) && checkOut.after(checkInDate);
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
        return "Reservation: \n" +
                "Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
                "Room: " + room.getRoomNumber() + "-" + room.getRoomType() + "\n" +
                "Price: " + room.getRoomPrice() + " price per night\n" +
                "Check In Date: " + formatter.format(checkInDate) + "\n" +
                "Check Out Date: " + formatter.format(checkOutDate) + "\n";
    }
}
