import javax.swing.*;

class MenuBarExample extends JFrame {
    private JTextArea textArea1;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;
    private JMenuItem newMenuItem, copyMenuItem, pasteMenuItem, cutMenuItem;
    private JPanel panel2;
    private JTextArea textArea2;

    public MenuBarExample() {
        setTitle("MenuBar Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel2);
        setVisible(true);
        pack();
        setResizable(true);


        newMenuItem.addActionListener(e -> {
            textArea1.setText("");
            textArea2.setText("");
        });
        copyMenuItem.addActionListener(e -> textArea1.copy());
        pasteMenuItem.addActionListener(e -> textArea2.paste());
        cutMenuItem.addActionListener(e -> textArea1.cut());
    }
}