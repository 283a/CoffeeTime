import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Controller {

    public static void main(String[] args) throws SocketException, UnknownHostException {

        Coffee coffee1 = new Coffee(false, false);
        Coffee coffee2 = new Coffee(false, false);

        InetSocketAddress inetSocketAddress1 = new InetSocketAddress(InetAddress.getLocalHost(),4446);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress(InetAddress.getLocalHost(),4445);

        CoffeeSocket socket1 = new CoffeeSocket(4445);
        CoffeeSocket socket2 = new CoffeeSocket(4446);

        CoffeeGui coffeeGui1 = new CoffeeGui();
        CoffeeGui coffeeGui2 = new CoffeeGui();

        socket1.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent pce) {
                System.out.println("here");
                coffee1.setRemote(socket1.getBuf()[0] == 1 ? true : false);
                coffeeGui1.updateCoffeeIcon(coffee1);
            }});
//
//        socket1.addPropertyChangeListener(pce -> {
//            System.out.println("here");
//            coffee1.setRemote(socket1.getBuf()[0] == 1 ? true : false);
//            testGui1.updateCoffeeIcon(coffee1);
//        });
        socket2.addPropertyChangeListener(pce -> {
            System.out.println("here2");
            coffee2.setRemote(socket2.getBuf()[0] == 1 ? true : false);
            coffeeGui2.updateCoffeeIcon(coffee2);
        });

        coffeeGui1.addPropertyChangeListener(pce -> {
            try {
                coffee1.setLocal(!coffee1.isLocal());
                socket1.setBuf(new byte[]{(byte) (coffee1.isLocal() ? 1 : 0 )});
                coffeeGui1.updateCoffeeIcon(coffee1);
                socket1.send(inetSocketAddress1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        coffeeGui2.addPropertyChangeListener(pce -> {
            try {
                coffee2.setLocal(!coffee2.isLocal());
                socket2.setBuf(new byte[]{(byte) (coffee2.isLocal() ? 1 : 0 )});
                coffeeGui2.updateCoffeeIcon(coffee2);
                socket2.send(inetSocketAddress2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        socket1.start();
        socket2.start();
    }

}
