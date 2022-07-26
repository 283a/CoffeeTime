import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class CoffeeGui {

    TrayIcon trayIcon = null;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public CoffeeGui() {
        if (SystemTray.isSupported()) {

            ActionListener exitListener = e -> {
                removeTrayIcon();
                System.out.println("Exiting...");
                System.exit(0);
            };

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem2 = new MenuItem("IP: 12431412241");
            MenuItem defaultItem = new MenuItem("Quit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem2);
            popup.add(defaultItem);

            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png"));
            trayIcon = new TrayIcon(image, "Coffee Time",popup);
            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        PropertyChangeEvent evt = new PropertyChangeEvent(this, "UI", 1, 0);
                        pcs.firePropertyChange(evt);
                    }
                }
                public void mouseEntered(MouseEvent e) {
                }
                public void mouseExited(MouseEvent e) {
                }
                public void mousePressed(MouseEvent e) {
                }
                public void mouseReleased(MouseEvent e) {
                }
            };
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void updateCoffeeIcon(Coffee coffee){
        if (coffee.both()) {
            trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("greenCoffee.png")));
        } else if (coffee.one()) {
            trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("orangeCoffee.png")));
        } else{
            trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png")));
        }
    }

    public void removeTrayIcon(){
            SystemTray tray = SystemTray.getSystemTray();
            tray.remove(trayIcon);
    }

}
