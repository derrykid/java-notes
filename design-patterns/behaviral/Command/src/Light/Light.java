package Light;

public class Light {
    private boolean switchedOn;

    public void switchLights(){
        switchedOn = !switchedOn;
        var isOn = switchedOn ? "On" : "Off";
        System.out.println("Light is: " + isOn);
    }
}
