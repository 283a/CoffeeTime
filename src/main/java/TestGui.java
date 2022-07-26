import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TestGui extends JFrame implements Runnable{
    CoffeeSocket socket1 = new CoffeeSocket(4445);
    CoffeeSocket socket2 = new CoffeeSocket(4446);


    public TestGui() throws SocketException {
        socket1.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent pce) {
                System.out.println("Wtfffffffffffffffffffffffffffffffffffffffffff");
        }});
        socket2.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent pce) {
                System.out.println("LLLLLLLLLLLLLLLLLLLLLLOooooooooooooooooooooLLLLLLLLL");
            }});
        setLayout(new GridLayout());
        setTitle("My Gui");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton jButton = new JButton("socket1");
        jButton.addActionListener(e -> {
            try {
                socket1.send(new InetSocketAddress(InetAddress.getLocalHost(),4446));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton jButton2 = new JButton("socket2");
        jButton2.addActionListener(e -> {
            try {
                socket2.send(new InetSocketAddress(InetAddress.getLocalHost(),4445));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        add(jButton);
        add(jButton2);

        socket1.start();
        socket2.start();
    }

    public static void main(String[] args) throws SocketException {
        TestGui testGui = new TestGui();
    }

    @Override
    public void run() {

    }
}
