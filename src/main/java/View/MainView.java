package View;

import Controller.PasswordManagerController;
import Model.PasswordManagerModel;
import Util.PasswordGenerator;
import Util.PasswordStrengthChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Map;

public class MainView {
    private PasswordManagerModel model;
    private PasswordManagerController controller;

    private JFrame frame;
    private JTextArea passwordListTextArea;
    private JTextField newPasswordTextField;
    private JTextField userTextField;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel mainLabel;
    private JLabel comunicationLabel;
    private JLabel passwordStrongLabel;
    private JButton addButton;
    private JButton removeButton;
    private JButton generateButton;
    private JScrollPane scrollPane;

    public MainView(PasswordManagerModel model) {
        this.model = model;
        initializeUI();
    }

    // Constructor with model and controller
    public MainView(PasswordManagerModel model, PasswordManagerController controller) {
        this.model = model;
        this.controller = controller;
        this.controller.setMainView(this);
        initializeUI();
    }

    public void setController(PasswordManagerController controller) {

        this.controller = controller;
    }

    // Initialize the user interface
    private void initializeUI() {
        frame = new JFrame("Password Manager");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        passwordListTextArea = new JTextArea();
        scrollPane = new JScrollPane(passwordListTextArea);
        newPasswordTextField = new JTextField();
        userTextField = new JTextField();
        generateButton = new JButton("Generate password");
        addButton = new JButton("Add password");
        removeButton = new JButton("Remove selected");
        userLabel = new JLabel("enter the user to add: ");
        passLabel = new JLabel("enter the password to add: ");
        mainLabel = new JLabel("Password list: ");
        comunicationLabel = new JLabel("");
        passwordStrongLabel = new JLabel("");

        generateButton.setBounds(320, 450, 200, 25);
        scrollPane.setBounds(50, 50, 300, 200);
        userLabel.setBounds(50, 275, 200, 25);
        comunicationLabel.setBounds(290, 200, 300, 200);
        passwordStrongLabel.setBounds(290, 250, 300, 200);
        userTextField.setBounds(50, 300, 200, 25);
        passLabel.setBounds(50, 325, 200, 25);
        mainLabel.setBounds(50, 10, 200, 25);
        newPasswordTextField.setBounds(50, 350, 200, 25);
        addButton.setBounds(50, 400, 200, 25);
        removeButton.setBounds(50, 450, 200, 25);
        comunicationLabel.setFont(new Font(null, Font.ITALIC, 16));
        passwordStrongLabel.setFont(new Font(null, Font.ITALIC, 19));

        frame.setLayout(null);
        frame.add(scrollPane);
        frame.add(newPasswordTextField);
        frame.add(userTextField);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(userLabel);
        frame.add(passLabel);
        frame.add(generateButton);
        frame.add(comunicationLabel);
        frame.add(passwordStrongLabel);
        frame.add(mainLabel);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);


        // Update password list
        updatePasswordList();

        frame.setVisible(true);

        // ActionListener for the "Add" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String newPassword = newPasswordTextField.getText();
                if (controller != null) {
                    controller.addPassword(username, newPassword);
                    newPasswordTextField.setText("");
                    userTextField.setText("");
                    updatePasswordList();
                    comunicationLabel.setText("Add user: " + username  + " password: " + newPassword);
                    comunicationLabel.setForeground(Color.blue);

                } else {
                    System.out.println("Controller is null.");
                }
                PasswordStrengthChecker.PasswordStrength strength = PasswordStrengthChecker.checkPasswordStrength(newPassword);
                handlePasswordStrength(strength);
            }
        });

        // ActionListener for the "Remove" button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUser = userTextField.getText().trim();
                String selectedPassword = newPasswordTextField.getText().trim();

                if (!selectedUser.isEmpty() && !selectedPassword.isEmpty()) {
                    // Wywołaj metodę kontrolera do usunięcia hasła
                    controller.removePassword(selectedUser);
                    // Zaktualizuj listę haseł po usunięciu
                    updatePasswordList();
                } else {
                    // Komunikat lub działanie w przypadku braku zaznaczenia
                    System.out.println("No password selected for removal.");
                }

            }
        });

        // ActionListener for the "Generate Password" button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = PasswordGenerator.generatePassword(12);
                newPasswordTextField.setText(newPassword);

            }
        });


    }

    // Update the displayed password list
    public void updatePasswordList() {
        Map<String, String> passwords = model.getPasswordMap();
        StringBuilder passwordList = new StringBuilder();
        for (Map.Entry<String, String> entry : passwords.entrySet()) {
            passwordList.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        passwordListTextArea.setText(passwordList.toString());
    }

    // Handle the display of password strength label
    private void handlePasswordStrength(PasswordStrengthChecker.PasswordStrength strength) {
        switch (strength) {
            case WEAK:
                passwordStrongLabel.setText("Password strength: WEAK");
                passwordStrongLabel.setForeground(Color.RED);
                break;
            case MEDIUM:
                passwordStrongLabel.setText("Password strength: MEDIUM");
                passwordStrongLabel.setForeground(Color.ORANGE);
                break;
            case STRONG:
                passwordStrongLabel.setText("Password strength: STRONG");
                passwordStrongLabel.setForeground(Color.GREEN);
                break;
        }
    }
}
