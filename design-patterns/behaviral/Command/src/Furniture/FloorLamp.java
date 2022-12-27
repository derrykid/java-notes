package Furniture;

import Light.Light;

public class FloorLamp {
    private Light light;
    public FloorLamp(){
        this.light = new Light();
    }
    public void switchLights(){
        light.switchLights();
    }
}
