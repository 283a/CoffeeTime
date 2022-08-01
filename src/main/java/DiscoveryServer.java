import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class DiscoveryServer implements Runnable{

    public static void main(String[] args) {
        DiscoveryServer discoveryServer = new DiscoveryServer();
        discoveryServer.run();
    }

    @Override
    public void run() {
        DatagramSocket socket = null;
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName( "0.0.0.0" );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket = new DatagramSocket(8887, addr);
            socket.setBroadcast(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true){
            byte[] receiveBuf = new byte[1];
            DatagramPacket packet = new DatagramPacket(receiveBuf,receiveBuf.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("address " + clientAddress.toString() + " port " + clientPort);
        }
    }
}
