import java.net.*;

public class DiscoveryClient{

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = null;
        byte[] receiveBuffer = new byte[1];
        byte byte1 = 2;
//        System.out.println();
//        byte1 = (byte) (byte1 | ~(1 << 0));
//        byte1 = (byte) (byte1 | ~(1 << 1));
        receiveBuffer[0] = byte1;

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
