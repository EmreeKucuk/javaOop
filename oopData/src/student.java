import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class student extends JFrame{
    private JPanel mainPanel;
    private JTable table;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JRadioButton studentRadioButton;
    private JRadioButton academicianRadioButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField courseCodeField;
    private Connection connection = null;
    private int selectedId = -1;

    public student() {
        setTitle("JTabbedPane Example");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
        pack();
        setResizable(true);

        connectToDatabase();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertRecord();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(studentRadioButton);
        group.add(academicianRadioButton);

        loadTableData();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(table.getValueAt(row, 0).toString());
                    nameField.setText(table.getValueAt(row, 1).toString());
                    surnameField.setText(table.getValueAt(row, 2).toString());
                    courseCodeField.setText(table.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/student";
            String user = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Database connection failed: " + e.getMessage());
        }
    }

    private void insertRecord() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String courseCode = courseCodeField.getText();

        if (studentRadioButton.isSelected()) {
            String sql = "INSERT INTO Student (Name, Surname, CourseCode) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setString(3, courseCode);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Student inserted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Insert failed: " + e.getMessage());
            }
        } else if (academicianRadioButton.isSelected()) {
            String sql = "INSERT INTO Academician (Name, Surname, CourseCode) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setString(3, courseCode);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Academician inserted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Insert failed: " + e.getMessage());
            }
        }

        loadTableData();
    }

    private void deleteRecord() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a record to delete");
            return;
        }

        if (studentRadioButton.isSelected()) {
            String sql = "DELETE FROM Student WHERE StudentID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, selectedId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Student deleted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Delete failed: " + e.getMessage());
            }
        } else if (academicianRadioButton.isSelected()) {
            String sql = "DELETE FROM Academician WHERE AcademicianID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, selectedId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Academician deleted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Delete failed: " + e.getMessage());
            }
        }

        selectedId = -1;
        loadTableData();
    }

    private void updateRecord() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a record to update");
            return;
        }

        String name = nameField.getText();
        String surname = surnameField.getText();
        String courseCode = courseCodeField.getText();

        if (studentRadioButton.isSelected()) {
            String sql = "UPDATE Student SET Name = ?, Surname = ?, CourseCode = ? WHERE StudentID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setString(3, courseCode);
                stmt.setInt(4, selectedId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Student updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Update failed: " + e.getMessage());
            }
        } else if (academicianRadioButton.isSelected()) {
            String sql = "UPDATE Academician SET Name = ?, Surname = ?, CourseCode = ? WHERE AcademicianID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setString(3, courseCode);
                stmt.setInt(4, selectedId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Academician updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Update failed: " + e.getMessage());
            }
        }

        selectedId = -1;
        loadTableData();
    }

    private void loadTableData() {
        String sql = studentRadioButton.isSelected() ? "SELECT * FROM Student" : "SELECT * FROM Academician";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            table.setModel(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Load data failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
       new student();
    }

    // Utility method to convert ResultSet to TableModel
    private static TableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        ArrayList<String> columnNames = new ArrayList<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        while (rs.next()) {
            ArrayList<Object> row = new ArrayList<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(rs.getObject(columnIndex));
            }
            data.add(row);
        }

        DefaultTableModel model = new DefaultTableModel();
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
        for (ArrayList<Object> rowData : data) {
            model.addRow(rowData.toArray());
        }
        return model;
    }
}
