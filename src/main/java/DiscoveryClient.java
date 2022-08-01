import java.io.IOException;
import java.net.*;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscoveryClient{

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = null;
        byte[] receiveBuffer = new byte[1];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);

        socket = new DatagramSocket();
        socket.setBroadcast(true);

        InetAddress broadcastAddress = null;
        broadcastAddress = InetAddress.getByName("255.255.255.255");
        DatagramPacket packet = new DatagramPacket(receiveBuffer,
                receiveBuffer.length, broadcastAddress, 8887);
        socket.send(packet);
        System.out.println("wtf");
    }

}
