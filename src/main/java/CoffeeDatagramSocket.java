import java.io.IOException;
import java.net.*;

public class CoffeeDatagramSocket extends Thread{
    private DatagramSocket socket;
    private InetAddress address;
    private boolean running;

    private byte[] buf;

    public CoffeeDatagramSocket(int port, InetAddress address) throws SocketException, UnknownHostException {
//        socket = new DatagramSocket(4445);
        socket = new DatagramSocket(port);
        this.address = address;
        buf = new byte[1];
    }

    public void send() throws IOException {
        buf[0] = 1;
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        System.out.println("send");
    }

    public void receive() throws IOException {
        DatagramPacket packet =  new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        System.out.println("received");
//        buf = packet.getData();
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
