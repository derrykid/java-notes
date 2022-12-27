import Command.SwitchLightsCommand;
import Light.Light;
import Room.*;

public class CommandDemo {
    public static void main(String[] args) {
        House house = new House();
        Room livingRoom = new LivingRoom();
        livingRoom.setCommand(new SwitchLightsCommand(new Light()));
        livingRoom.executeCommand();

    }
}
