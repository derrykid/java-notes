### Keyboard keylistener

Create a class `implements KeyListener`

**keyPressed and keyTyped** methods are required.

```java
public class BirdKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_SPACE) {
            System.out.println("You pressed spacekey");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // when release, TODO
    }

    @Override
    public void keyTyped(Keyevent e) {
        // TODO
    }

}
```
