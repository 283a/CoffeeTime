import java.io.IOException;
import java.net.*;

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
//            addr = InetAddress.getByName( "0.0.0.0" );
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket = new DatagramSocket(8887, addr);
//            socket.setBroadcast(true);
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
            byte[] bb = packet.getData();
            System.out.println(bb[0]);
//            System.out.println(getBit(0,bb[0]));
//            System.out.println(getBit(1,bb[0]));
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("address " + clientAddress.toString() + " port " + clientPort);
        }
    }
    public short getBit(int position,byte b)
    {
        return (short) ((b >> position) & 1);
    }
}
