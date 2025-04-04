package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Set<Reservation> reservations = new HashSet<>();
    private final static ReservationService reservationService = new ReservationService();
    public static ReservationService getInstance() {
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation res : reservations) {
            if (res.getRoom().equals(room) && res.isOverlap(checkInDate, checkOutDate)) {
                throw new IllegalArgumentException("Room is already booked for the given date range.");
            }
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Set<IRoom> unavailableRooms = new HashSet<>();
        for (Reservation reservation : reservations) {
            if (reservation.isOverlap(checkInDate, checkOutDate)) {
                unavailableRooms.add(reservation.getRoom());
            }
        }

        // 可用房間 = 所有房間 - 不可用房間
        List<IRoom> availableRooms = new ArrayList<>(rooms.values());
        availableRooms.removeAll(unavailableRooms);
        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservations(Customer customer) {
        Collection<Reservation> customerReservations = new HashSet<>();
        for (Reservation reservation : reservations) {
            if(reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
