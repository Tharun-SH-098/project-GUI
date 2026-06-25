import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TaskManagementSystem extends JFrame {

    private JTextField taskField;
    private JComboBox<String> priorityBox;
    private JTable taskTable;
    private DefaultTableModel model;

    public TaskManagementSystem() {

        setTitle("Task Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());

        JLabel taskLabel = new JLabel("Task:");
        taskField = new JTextField(20);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityBox = new JComboBox<>(
                new String[]{"High", "Medium", "Low"});

        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");
        JButton deleteButton = new JButton("Delete Task");

        topPanel.add(taskLabel);
        topPanel.add(taskField);
        topPanel.add(priorityLabel);
        topPanel.add(priorityBox);
        topPanel.add(addButton);
        topPanel.add(completeButton);
        topPanel.add(deleteButton);

        String[] columns = {
                "Task ID",
                "Task Name",
                "Priority",
                "Status"
        };

        model = new DefaultTableModel(columns, 0);

        taskTable = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(taskTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addTask());

        completeButton.addActionListener(
                e -> markCompleted());

        deleteButton.addActionListener(
                e -> deleteTask());

        setVisible(true);
    }

    private void addTask() {

        String taskName = taskField.getText().trim();

        if (taskName.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a task!"
            );
            return;
        }

        int taskId = model.getRowCount() + 1;

        String priority =
                priorityBox.getSelectedItem().toString();

        model.addRow(new Object[]{
                taskId,
                taskName,
                priority,
                "Pending"
        });

        taskField.setText("");
    }

    private void markCompleted() {

        int row = taskTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Select a task first!"
            );
            return;
        }

        model.setValueAt(
                "Completed",
                row,
                3
        );
    }

    private void deleteTask() {

        int row = taskTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Select a task first!"
            );
            return;
        }

        model.removeRow(row);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new TaskManagementSystem());
    }
}