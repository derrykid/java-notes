package factory;

import buttons.Button;

public abstract class Dialog {
    public void renderWindow(){
        Button okButton = createButton();
        okButton.render();
    }

    /*
    *   A:
    *   return the final product (here is Button)
    *   Match the product interface
    *
    *   B.1:
    *   declare as abstract method, so the subclass
    *   must implement their own version
    *
    *   B.2:
    *   Or, remove abstract from signature,
    *   return one default as your desire
    * */
    public abstract Button createButton();
}
