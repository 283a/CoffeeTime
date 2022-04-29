import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Controller extends Thread{
    FrameIcon frameIcon;
    Coffee coffee;
    CoffeeDatagramSocket coffeeDatagramSocket;

    public Controller() throws UnknownHostException, SocketException {
        this.coffee = new Coffee(false,false,InetAddress.getByName("192.168.178.26"));
        this.coffeeDatagramSocket = new CoffeeDatagramSocket(4445, coffee);
        this.frameIcon = new FrameIcon(coffee,coffeeDatagramSocket);
        coffeeDatagramSocket.start();
    }

    public static void main(String[] args) {
        try {
            new Controller().start();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            frameIcon.updateIcon();
        }
    }
}
