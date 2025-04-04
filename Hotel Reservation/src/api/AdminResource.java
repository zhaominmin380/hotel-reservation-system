package api;

import service.CustomerService;
import service.ReservationService;

import model.*;
import java.util.*;

public class AdminResource {
    private final static AdminResource reference = new AdminResource();
    private AdminResource() {}
    public static AdminResource getInstance() {
        return reference;
    }

    public void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            ReservationService.getInstance().addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return ReservationService.getInstance().findRooms(new java.util.Date(0), new java.util.Date(Long.MAX_VALUE));
    }

    public Collection<Customer> getAllCustomers() {
        return CustomerService.getInstance().getAllCustomers();
    }

    public void displayAllReservations() {
        ReservationService.getInstance().printAllReservations();
    }
}
