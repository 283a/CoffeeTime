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
        this.coffee = new Coffee(false,false,InetAddress.getByName("192.168.178.21"));
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

        boolean prevLocal = coffee.isLocal();
        boolean prevRemote = coffee.isRemote();
        while (true){
//            System.out.println("=>" + (coffee.isLocal() != prevLocal) + " : " + coffee.isLocal() + " : " + prevLocal);
//            if(coffee.isLocal() != prevLocal || coffee.isRemote() != prevRemote) {
//                prevLocal = coffee.isLocal();
//                prevRemote = coffee.isRemote();
//                System.out.println("fuck");
//
//                try {
//                    Thread.sleep(1800);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frameIcon.updateIcon();
        }
    }
}
