import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Math");
        String[] operations = {"Addition", "Subtraction", "Multiplication", "Division"};

        int operationChoice;
        int confirm;

        do {
            operationChoice = JOptionPane.showOptionDialog(frame, "Select an operation:", "Math", 0, 3, null, operations, operations[0]);
            confirm = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    double num1 = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter first number:"));
                    double num2 = 0;
                    double result = 0;

                    if (operationChoice == 3) {
                        do {
                            num2 = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter second number:"));
                            if (num2 == 0) {
                                JOptionPane.showMessageDialog(frame, "Second number can not be zero.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } while (num2 == 0);
                        result = num1 / num2;
                    } else {
                        num2 = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter second number:"));
                        if (operationChoice == 0)
                            result = num1 + num2;
                        else if (operationChoice == 1)
                            result = num1 - num2;
                        else if (operationChoice == 2)
                            result = num1 * num2;
                    }

                    JOptionPane.showMessageDialog(frame, "Result: " + result, "Result", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ArithmeticException e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (confirm != JOptionPane.YES_OPTION);


        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}