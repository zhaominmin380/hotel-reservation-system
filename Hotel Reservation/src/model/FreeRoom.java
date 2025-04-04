package model;

public class FreeRoom extends Room {
    FreeRoom(String roomNumber, RoomType enumeration){
        super(roomNumber, 0.0, enumeration);
    }

    public Double getRoomPrice(){
        return 0.0;
    }

    public String toString(){
        return "FreeRoom: RoomNumber = " + getRoomNumber() + ", RoomType = " + getRoomType() + ", Price = " + getRoomPrice();
    }
}
