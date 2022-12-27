package Room;

import Command.*;

public class Room {
    Command command;
    public void setCommand(Command cmd){
        this.command = cmd;
    }

    public void executeCommand(){
        this.command.exec();
    }
}
