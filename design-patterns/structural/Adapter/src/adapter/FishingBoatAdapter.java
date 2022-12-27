package adapter;

import FishingBoat.FishingBoat;
import RowingBoat.RowingBoat;

import java.util.Collection;

public class FishingBoatAdapter implements RowingBoat {

    private final FishingBoat fishingBoat;

    public FishingBoatAdapter(){
        fishingBoat = new FishingBoat();
    }

    @Override
    public void row() {
        fishingBoat.sail();
    }
}
