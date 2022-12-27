```java
File file = new File("~/audio/some.wav");
AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
Clip clip = AudioSystem.getClip();
clip.open(audioStream);
clip.start();
```
