import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Controller {

    public static void main(String[] args) {
        try {

            Coffee coffee = new Coffee(false,false,InetAddress.getByName("192.168.178.21"));
            CoffeeDatagramSocket coffeeDatagramSocket = new CoffeeDatagramSocket(4445, coffee);
            FrameIcon frameIcon = new FrameIcon(coffee,coffeeDatagramSocket);

            coffeeDatagramSocket.start();

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
