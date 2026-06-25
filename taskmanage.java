import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TaskManagementSystem extends JFrame {

    private JTextField taskField;
    private JComboBox<String> priorityBox;
    private JTable taskTable;
    private DefaultTableModel model;

    public TaskManagementSystem() {

        setTitle("Task Management System");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(24, 28, 33));

        // Header
        JLabel title = new JLabel("TASK MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 118, 110));
        header.setPreferredSize(new Dimension(1000, 70));
        header.add(title);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(35, 39, 47));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        taskField = new JTextField(20);
        taskField.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        priorityBox = new JComboBox<>(
                new String[]{"High", "Medium", "Low"});
        priorityBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JButton addBtn = createButton("Add Task", new Color(34, 197, 94));
        JButton completeBtn = createButton("Complete", new Color(59, 130, 246));
        JButton deleteBtn = createButton("Delete", new Color(239, 68, 68));

        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskField);
        inputPanel.add(priorityBox);
        inputPanel.add(addBtn);
        inputPanel.add(completeBtn);
        inputPanel.add(deleteBtn);

        // Table
        String[] cols = {
                "ID",
                "Task Name",
                "Priority",
                "Status"
        };

        model = new DefaultTableModel(cols, 0);

        taskTable = new JTable(model);
        taskTable.setRowHeight(30);
        taskTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTableHeader headerTable = taskTable.getTableHeader();
        headerTable.setBackground(new Color(15, 118, 110));
        headerTable.setForeground(Color.WHITE);
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(taskTable);

        // Statistics Panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        statsPanel.setBackground(new Color(24, 28, 33));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statsPanel.add(createCard("Total Tasks", "0"));
        statsPanel.add(createCard("Completed", "0"));
        statsPanel.add(createCard("Pending", "0"));

        // Events
        addBtn.addActionListener(e -> addTask());
        completeBtn.addActionListener(e -> completeTask());
        deleteBtn.addActionListener(e -> deleteTask());

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.WEST);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(120, 40));
        return btn;
    }

    private JPanel createCard(String title, String value) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(45, 55, 72));
        panel.setPreferredSize(new Dimension(180, 120));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setForeground(new Color(34, 197, 94));
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    private void addTask() {
        String task = taskField.getText();

        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter task");
            return;
        }

        model.addRow(new Object[]{
                model.getRowCount() + 1,
                task,
                priorityBox.getSelectedItem(),
                "Pending"
        });

        taskField.setText("");
    }

    private void completeTask() {
        int row = taskTable.getSelectedRow();

        if (row >= 0) {
            model.setValueAt("Completed", row, 3);
        }
    }

    private void deleteTask() {
        int row = taskTable.getSelectedRow();

        if (row >= 0) {
            model.removeRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                TaskManagementSystem::new
        );
    }
}