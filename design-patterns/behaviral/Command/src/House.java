import Room.Room;

import java.util.ArrayList;
import java.util.List;

public class House {
    List<Room> rooms;
    public House(){
        rooms = new ArrayList<Room>();
    }

    public void addRoom(Room room){
        this.rooms.add(room);
    }
}
