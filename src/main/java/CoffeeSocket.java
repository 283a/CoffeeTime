import javax.sound.sampled.Port;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.*;

public class CoffeeSocket extends Thread {
    private int port;
    private byte[] buf;
    private DatagramSocket socket;
    boolean remote = false;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);


    public CoffeeSocket(int port) throws SocketException {
        this.port = port;
        buf = new byte[1];
        socket = new DatagramSocket(port);
    }

    public void send(InetSocketAddress inetSocketAddress) throws IOException {
        System.out.println("send"+ buf[0]);
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
        socket.send(packet);
    }

    public void receive() throws IOException {
        byte oldRemote = 0;
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        buf = packet.getData();
        remote = buf[0] == 1 ? true : false;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, "remote", oldRemote, remote);
        pcs.firePropertyChange(evt);

        System.out.println("receive " + buf[0] + " " + oldRemote);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
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
