import java.awt.*;
import java.io.IOException;
import java.net.*;

public class CoffeeDatagramSocket extends Thread{
    private DatagramSocket socket;
    private Coffee coffee;
    private InetAddress address;
    private boolean running;

    private byte[] buf;

    public CoffeeDatagramSocket(int port, Coffee coffee) throws SocketException {
        socket = new DatagramSocket(port);
        this.coffee = coffee;
        this.address = coffee.getAddress();
        buf = new byte[1];
    }

    public void send() throws IOException {
        coffee.setLocal(!coffee.isLocal());
        buf[0] = (byte) (coffee.isLocal() ? 1 : 0 );
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        System.out.println("send " + buf[0]);
    }

    public void receive() throws IOException {
        DatagramPacket packet =  new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        buf = packet.getData();
        coffee.setRemote(buf[0] == 1 ? true : false);
        System.out.println("received " + buf[0]);
    }

    public void close() {
        socket.close();
    }

    public void run() {
        running = true;

        while (running) {
            try {
                receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
