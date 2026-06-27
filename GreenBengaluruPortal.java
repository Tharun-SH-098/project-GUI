import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class GreenBengaluruPortal extends JFrame {

    private JTextField nameField;
    private JTextField locationField;
    private JComboBox<String> activityBox;
    private JTable table;
    private DefaultTableModel model;

    public GreenBengaluruPortal() {

        setTitle("Green Bengaluru Development Portal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("GREEN BENGALURU DEVELOPMENT PORTAL", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 120, 0));

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.setBorder(BorderFactory.createTitledBorder("Volunteer Registration"));

        formPanel.add(new JLabel("Volunteer Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        formPanel.add(new JLabel("Activity:"));

        String[] activities = {
                "Tree Plantation",
                "Lake Cleaning",
                "Waste Management",
                "Water Conservation",
                "Solar Awareness"
        };

        activityBox = new JComboBox<>(activities);
        formPanel.add(activityBox);

        JButton registerBtn = new JButton("Register Volunteer");
        JButton clearBtn = new JButton("Clear");

        formPanel.add(registerBtn);
        formPanel.add(clearBtn);

        mainPanel.add(formPanel, BorderLayout.WEST);

        model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Location");
        model.addColumn("Activity");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel dashboardPanel = new JPanel(new GridLayout(4,1));

        dashboardPanel.setBorder(
                BorderFactory.createTitledBorder("Green Dashboard"));

        JLabel trees = new JLabel("🌳 Trees Planted : 15,00,000");
        JLabel water = new JLabel("💧 Water Saved : 10 Million Liters");
        JLabel waste = new JLabel("♻ Waste Recycled : 5000 Tons");
        JLabel volunteers = new JLabel("👥 Volunteers : 50,000");

        dashboardPanel.add(trees);
        dashboardPanel.add(water);
        dashboardPanel.add(waste);
        dashboardPanel.add(volunteers);

        mainPanel.add(dashboardPanel, BorderLayout.EAST);

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String location = locationField.getText();
                String activity = activityBox.getSelectedItem().toString();

                if(name.isEmpty() || location.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            GreenBengaluruPortal.this,
                            "Please fill all fields");
                    return;
                }

                model.addRow(new Object[]{
                        name,
                        location,
                        activity
                });

                JOptionPane.showMessageDialog(
                        GreenBengaluruPortal.this,
                        "Volunteer Registered Successfully!");

                nameField.setText("");
                locationField.setText("");
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                locationField.setText("");
            }
        });

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);

        JMenu reportMenu = new JMenu("Reports");

        JMenuItem statsItem = new JMenuItem("View Statistics");

        statsItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    GreenBengaluruPortal.this,
                    "Trees Planted: 15,00,000\n" +
                    "Water Saved: 10 Million Liters\n" +
                    "Waste Recycled: 5000 Tons\n" +
                    "Volunteers: 50,000");
        });

        reportMenu.add(statsItem);

        menuBar.add(fileMenu);
        menuBar.add(reportMenu);

        setJMenuBar(menuBar);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new GreenBengaluruPortal();
        });

    }
}