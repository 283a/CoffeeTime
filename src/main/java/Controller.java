import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Controller {

    public static void main(String[] args) {
        try {

            FrameIcon frameIcon = new FrameIcon();
            CoffeeDatagramSocket coffeeDatagramSocket = new CoffeeDatagramSocket(4445, InetAddress.getLocalHost());
            coffeeDatagramSocket.start();
            while (true){
                TimeUnit.SECONDS.sleep(1);
                if(frameIcon.coffeeTime1 && frameIcon.coffeeTime2){

                    System.out.println(frameIcon.coffeeTime1 || frameIcon.coffeeTime2);
                    coffeeDatagramSocket.send();
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
