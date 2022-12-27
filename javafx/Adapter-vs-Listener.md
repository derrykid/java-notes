We might want to add a listener to an event, but it sometimes have a lot methods we have to @Override. We can just use `Adapter`

Listener
```java
    mainFrame.addWindowListener(new WindowListener() {

        @Override
        public void windowClosing(WindowEvent e) {
            if (JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to quit?", "Confirm exit.", JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
                return;
            }
            System.exit(-1);
        }

        @Override 
        public void windowOpened(WindowEvent e) {}

        @Override 
        public void windowClosed(WindowEvent e) {}

        @Override 
        public void windowIconified(WindowEvent e) {}

        @Override 
        public void windowDeiconified(WindowEvent e) {}

        @Override 
        public void windowActivated(WindowEvent e) {}

        @Override 
        public void windowDeactivated(WindowEvent e) {}

    });
```

With adapter
```java
   mainFrame.addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(-1);
        }
    });
```
