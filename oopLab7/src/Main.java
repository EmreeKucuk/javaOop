import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 7");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new java.awt.Color(188, 255, 186));

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 20, 500, 100);
        textArea.setBackground(new java.awt.Color(255, 164, 164));

        JRadioButton radiobutton1 = new JRadioButton("Multiples of 3 and 5");
        radiobutton1.setBounds(50, 130, 150, 20);

        JRadioButton radiobutton2 = new JRadioButton("Prime Numbers");
        radiobutton2.setBounds(50, 160, 150, 20);

        ButtonGroup group = new ButtonGroup();
        group.add(radiobutton1);
        group.add(radiobutton2);

        JButton showButton = new JButton("SHOW");
        showButton.setBounds(0, 0, 100, 30);

        ImageIcon flowerIcon = new ImageIcon("flower.png");
        JLabel flowerLabel = new JLabel(flowerIcon);
        flowerLabel.setBounds(50,190,20,20);

        JPanel panel = new JPanel();
        panel.setBounds(50, 200, 150, 30);
        panel.add(flowerLabel);
        panel.add(showButton);

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                if (radiobutton1.isSelected()) {
                    for (int i = 0; i <= 100; i++) {
                        if (i % 3 == 0 && i % 5 == 0) {
                            if (!result.isEmpty()) {
                                result += ", ";
                            }
                            result += i;
                        }
                    }
                } else if (radiobutton2.isSelected()) {
                    for (int i = 2; i <= 100; i++) {
                        if (isPrime(i)) {
                            if (!result.isEmpty()) {
                                result += ", ";
                            }
                            result += i;
                        }
                    }
                }
                textArea.setText(result);
            }
        });

        frame.add(radiobutton1);
        frame.add(radiobutton2);
        frame.add(panel);
        frame.add(textArea);
        frame.setVisible(true);
    }
    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
