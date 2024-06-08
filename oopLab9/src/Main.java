import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel homePanel, aboutPanel, loginPanel, infoPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, goToMenuButton;
    private JPanel panel1;

    public Main() {
        setTitle("JTabbedPane Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();
        setResizable(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.equals("user") && password.equals("1234")) {
                    goToMenuButton.setVisible(true);
                    tabbedPane.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid");
                }
            }
        });
        goToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuBarExample menuBarExample = new MenuBarExample();
                menuBarExample.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }

}


