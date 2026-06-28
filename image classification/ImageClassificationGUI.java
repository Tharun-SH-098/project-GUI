import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageClassificationGUI extends JFrame implements ActionListener {

    JLabel title, imageLabel, resultLabel;
    JButton uploadBtn, classifyBtn, clearBtn, exitBtn;
    JPanel topPanel, centerPanel, bottomPanel;
    File selectedFile;

    public ImageClassificationGUI() {

        setTitle("AI Image Classification System");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Panel
        topPanel = new JPanel();
        topPanel.setBackground(new Color(33,150,243));

        title = new JLabel("IMAGE CLASSIFICATION SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        topPanel.add(title);

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20,20,20,20));

        imageLabel = new JLabel("No Image Selected", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN,20));
        imageLabel.setBorder(new LineBorder(Color.GRAY,2));
        imageLabel.setPreferredSize(new Dimension(500,400));

        centerPanel.add(imageLabel,BorderLayout.CENTER);

        // Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3,1,10,10));
        bottomPanel.setBorder(new EmptyBorder(15,20,20,20));

        JPanel btnPanel = new JPanel();

        uploadBtn = new JButton("Upload Image");
        classifyBtn = new JButton("Classify");
        clearBtn = new JButton("Clear");
        exitBtn = new JButton("Exit");

        uploadBtn.addActionListener(this);
        classifyBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        btnPanel.add(uploadBtn);
        btnPanel.add(classifyBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(exitBtn);

        resultLabel = new JLabel("Classification Result : None", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD,22));
        resultLabel.setForeground(new Color(0,128,0));

        bottomPanel.add(btnPanel);
        bottomPanel.add(resultLabel);

        add(topPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==uploadBtn){

            JFileChooser chooser = new JFileChooser();

            chooser.setFileFilter(
                new FileNameExtensionFilter(
                    "Images","jpg","jpeg","png","bmp"));

            int option = chooser.showOpenDialog(this);

            if(option==JFileChooser.APPROVE_OPTION){

                selectedFile = chooser.getSelectedFile();

                ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

                Image img = icon.getImage().getScaledInstance(
                        450,
                        350,
                        Image.SCALE_SMOOTH);

                imageLabel.setIcon(new ImageIcon(img));
                imageLabel.setText("");
            }

        }

        else if(e.getSource()==classifyBtn){

            if(selectedFile==null){

                JOptionPane.showMessageDialog(this,
                        "Please Upload an Image");

                return;
            }

            // Dummy Classification
            String name = selectedFile.getName().toLowerCase();

            String prediction;

            if(name.contains("cat"))
                prediction="Cat";
            else if(name.contains("dog"))
                prediction="Dog";
            else if(name.contains("car"))
                prediction="Car";
            else if(name.contains("flower"))
                prediction="Flower";
            else
                prediction="Unknown Object";

            resultLabel.setText(
                    "Classification Result : " + prediction);

        }

        else if(e.getSource()==clearBtn){

            imageLabel.setIcon(null);
            imageLabel.setText("No Image Selected");
            resultLabel.setText("Classification Result : None");
            selectedFile=null;

        }

        else if(e.getSource()==exitBtn){

            System.exit(0);

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ImageClassificationGUI();
        });

    }
}