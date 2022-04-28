import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class FrameIcon {
    private Coffee coffee;
    private  CoffeeDatagramSocket coffeeDatagramSocket;

    public FrameIcon(Coffee coffee,CoffeeDatagramSocket coffeeDatagramSocket){
        this.coffee = coffee;
        this.coffeeDatagramSocket = coffeeDatagramSocket;
        if (SystemTray.isSupported()) {
            final TrayIcon trayIcon;

            SystemTray tray = SystemTray.getSystemTray();

            ActionListener exitListener = e -> {
                System.out.println("Exiting...");
                System.exit(0);
            };

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem2 = new MenuItem("IP: 12431412241");
            MenuItem defaultItem = new MenuItem("Quit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem2);
            popup.add(defaultItem);


            Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png"));
            trayIcon = new TrayIcon(image, "Coffee Time",popup);

            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(e.getButton());
                    if(e.getButton() == MouseEvent.BUTTON1){
                        try {
                            coffeeDatagramSocket.send();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println("Tray Icon - Mouse clicked!");
                        if(coffee.both()){
                            trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("greenCoffee.png")));
                        }   else if(coffee.one()){
                            trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("orangeCoffee.png")));
                        }   else {
                            trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png")));
                        }
                    }
                }

                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {}
            };

            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }

        SwingUtilities.invokeLater(() -> {});
    }

}