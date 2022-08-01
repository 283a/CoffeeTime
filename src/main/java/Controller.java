import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Controller {

    private Coffee coffee;
    private CoffeeSocket coffeeSocket;
    private CoffeeGui coffeeGui;
    private InetSocketAddress inetSocketAddress;

    public void setCoffeeSocket(CoffeeSocket coffeeSocket) {
        this.coffeeSocket = coffeeSocket;
    }

    public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
        this.inetSocketAddress = inetSocketAddress;
    }

    public Controller(int port) throws SocketException, UnknownHostException {
        this.coffee = new Coffee(false, false);
        this.coffeeSocket = new CoffeeSocket(port, coffee);
        this.coffeeGui = new CoffeeGui(coffee);
        this.inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 4446);

        coffee.addPropertyChangeListener(pce -> {
            if (pce.getPropertyName().equals("remote")) {
                coffeeGui.updateCoffeeIcon();
            } else if (pce.getPropertyName().equals("local")) {
                coffeeSocket.setBuf(new byte[]{(byte) (coffee.isLocal() ? 1 : 0)});
                try {
                    coffeeSocket.send(inetSocketAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void start() {
        coffeeSocket.start();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        Controller controller1 = new Controller(4445);
        Controller controller2 = new Controller(4446);
        controller2.setInetSocketAddress(new InetSocketAddress(InetAddress.getLocalHost(), 4445));
        controller1.start();
        controller2.start();
    }

}
