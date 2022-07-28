import java.io.IOException;
import java.net.*;

public class CoffeeSocket extends Thread {
    private byte[] buf;
    private DatagramSocket socket;
    private Coffee coffee;


    public CoffeeSocket(int port, Coffee coffee) throws SocketException {
        this.coffee = coffee;
        buf = new byte[1];
        socket = new DatagramSocket(port);
    }

    public void send(InetSocketAddress inetSocketAddress) throws IOException {
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
        socket.send(packet);
    }

    public void receive() throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        buf = packet.getData();

        coffee.setRemote(buf[0] == 1 ? true : false);
    }

    public void close() {
        socket.close();
    }

    public byte[] getBuf() {
        return buf;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }

    @Override
    public void run() {
        while (true) {
            try {
                receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
