import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CoffeeGui {
    private final Coffee coffee;
    TrayIcon trayIcon = null;

    public CoffeeGui(Controller controller) {

        this.coffee = controller.getCoffee();
        if (SystemTray.isSupported()) {

            ActionListener exitListener = e -> {
                removeTrayIcon();
                System.out.println("Exiting...");
                System.exit(0);
            };

            PopupMenu popup = new PopupMenu();

            MenuItem defaultItem2 = new MenuItem("Settings");
            MenuItem defaultItem = new MenuItem("Exit");
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
                        coffee.setLocal(!coffee.isLocal());
                        updateCoffeeIcon();
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
            defaultItem2.addActionListener(e -> {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SettingsGui(controller);
                    }
                });
            });
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }

    public void updateCoffeeIcon(){
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
