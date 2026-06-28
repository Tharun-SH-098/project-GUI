import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PDFChatbot extends JFrame implements ActionListener {

    JTextArea pdfArea, outputArea;
    JTextField questionField;
    JButton openBtn, simplifyBtn, clearBtn;

    public PDFChatbot() {

        setTitle("PDF Simplifier Chatbot");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();

        openBtn = new JButton("Open PDF");
        simplifyBtn = new JButton("Simplify");
        clearBtn = new JButton("Clear");

        topPanel.add(openBtn);
        topPanel.add(simplifyBtn);
        topPanel.add(clearBtn);

        add(topPanel, BorderLayout.NORTH);

        pdfArea = new JTextArea();
        pdfArea.setBorder(BorderFactory.createTitledBorder("PDF Content"));

        outputArea = new JTextArea();
        outputArea.setBorder(BorderFactory.createTitledBorder("Chatbot Output"));

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(pdfArea),
                new JScrollPane(outputArea));

        splitPane.setDividerLocation(380);

        add(splitPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        questionField = new JTextField();
        questionField.setBorder(BorderFactory.createTitledBorder("Ask a Question"));

        bottomPanel.add(questionField, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        openBtn.addActionListener(this);
        simplifyBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openBtn) {

            JFileChooser chooser = new JFileChooser();

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

                pdfArea.setText(
                        "Selected File:\n\n"
                        + chooser.getSelectedFile().getName()
                        + "\n\n(PDF reading not implemented in this simple version.)");

            }

        }

        if (e.getSource() == simplifyBtn) {

            String question = questionField.getText();

            if (question.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a question.");

                return;
            }

            outputArea.setText(
                    "Question:\n"
                    + question
                    + "\n\nSimple Answer:\n"
                    + "This is a simplified response.\n\n"
                    + "To get real answers, connect the application "
                    + "to a PDF reader and an AI model.");

        }

        if (e.getSource() == clearBtn) {

            pdfArea.setText("");
            outputArea.setText("");
            questionField.setText("");

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new PDFChatbot());

    }
}