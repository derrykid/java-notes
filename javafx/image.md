Under the hood, most programmers use `BufferedImage` for image rendering.

**ImageView** and **Image** 
simple image viewer
```java
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

Image imageFile = new Image("File:0.jpg");
ImageView image = new ImageView(imageFile);

// add image to GUI elements e.g. Pane, and set scene
```

**BufferedImage extends Image** 

1. Read local image
```java
File file = new File("001.jpg");
BufferedImage image = (BufferedImage)ImageIO.read(file);
```
