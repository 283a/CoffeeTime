import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class FrameIcon {
    boolean coffeeTime1 = false;
    boolean coffeeTime2 = false;

    public FrameIcon(){
        if (SystemTray.isSupported()) {
            final TrayIcon trayIcon;

            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png"));
            trayIcon = new TrayIcon(image, "Coffee Time");

            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");
                    if(coffeeTime1 && coffeeTime2){
                        trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("greenCoffee.png")));
                        coffeeTime1 = false;
                        coffeeTime2 = false;
                    }   else if(coffeeTime1 || coffeeTime2){
                        trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("orangeCoffee.png")));
                        coffeeTime1 = !coffeeTime1;
                    }   else {
                        trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png")));
                        coffeeTime2 = !coffeeTime2;
                    }
                }

                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
                public void mousePressed(MouseEvent e) {}
                @Override
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