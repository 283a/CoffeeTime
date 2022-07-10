import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

public class TestGui extends JFrame implements Runnable {
    TestSocket socket1 = new TestSocket(4445,4446,0);
    TestSocket socket2 = new TestSocket(4446,4445,1);
    public TestGui() throws SocketException {
        setLayout(new GridLayout());
        setTitle("My Gui");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton jButton = new JButton("socket1");
        jButton.addActionListener(e -> {
            try {
                socket1.send();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton jButton2 = new JButton("socket2");
        jButton2.addActionListener(e -> {
            try {
                socket2.send();
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
