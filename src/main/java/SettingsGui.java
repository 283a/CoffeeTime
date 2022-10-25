import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class SettingsGui extends JFrame{

    private File configFile = new File("src/main/resources/config.properties");
    private Properties configProps;

    private JLabel labelIP = new JLabel("IP Address: ");
    private JLabel labelPort = new JLabel("Port number: ");

    private JTextField textIP = new JTextField(20);
    private JTextField textPort = new JTextField(20);

    private JButton buttonSave = new JButton("Save");

    public SettingsGui() {
        super("Settings");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setName("Settings");
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("blackCoffee.png")));
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(labelIP, constraints);

        constraints.gridx = 1;
        add(textIP, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        add(labelPort, constraints);

        constraints.gridx = 1;
        add(textPort, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        add(buttonSave, constraints);
        buttonSave.setToolTipText("save");

        buttonSave.addActionListener(arg0 -> {
            try {
                saveProperties();
                JOptionPane.showMessageDialog(SettingsGui.this,
                        "Properties were saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(SettingsGui.this,
                        "Error saving properties file: " + ex.getMessage());
            }
        });

        pack();
        setVisible(true);
        try {
            loadProperties();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "The config.properties file does not exist, default properties loaded.");
        }

        textIP.setText(configProps.getProperty("ip"));
        textPort.setText(configProps.getProperty("port"));
    }

    private void loadProperties() throws IOException {
        Properties defaultProps = new Properties();
        // sets default properties
        defaultProps.setProperty("ip", "192.168.56.1");
        defaultProps.setProperty("port", "4445");

        configProps = new Properties(defaultProps);

        // loads properties from file
        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    private void saveProperties() throws IOException {
        configProps.setProperty("ip", textIP.getText());
        configProps.setProperty("port", textPort.getText());

        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "IP settings");
        outputStream.close();
    }
}
