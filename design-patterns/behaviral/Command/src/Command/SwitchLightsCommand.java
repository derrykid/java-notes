package Command;

import Light.Light;

public class SwitchLightsCommand implements Command{

    private Light light;

    public SwitchLightsCommand(Light light){
        this.light = light;
    }

    @Override
    public void exec() {
        light.switchLights();
    }
}
