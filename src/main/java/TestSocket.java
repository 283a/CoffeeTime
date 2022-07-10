import javax.security.auth.Subject;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.*;

public class TestSocket extends Thread {
    private byte[] buf;
    private DatagramSocket socket;
    boolean lol;
    int numba;
    private int reciverPort;

    public TestSocket(int port,int reciverPort, int numba) throws SocketException {
        this.reciverPort = reciverPort;
        buf = new byte[1];
        lol = true;
        socket = new DatagramSocket(port);
        this.numba = numba;
    }

    public void send() throws IOException {
        System.out.println("send");
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), reciverPort);
        socket.send(packet);
    }

    public void receive() throws IOException {
        DatagramPacket packet =  new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        buf = packet.getData();
        System.out.println("receive " + buf[0] + " number: "+ numba);
        lol = buf[0] == 1;
//        buf[0] = (byte) (lol ? 1 : 0);
        buf[0] = (byte) numba;
    }

    public void close() {
        socket.close();
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
                System.out.println("sleep" + numba);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
