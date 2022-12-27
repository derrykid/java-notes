package Captain;

import RowingBoat.RowingBoat;

public class Captain {

    private final RowingBoat rowingBoat;

    public Captain(RowingBoat boat){
        this.rowingBoat = boat;
    }

    public void row(){
        rowingBoat.row();
    }


}
