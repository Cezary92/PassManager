package View;

import Controller.PasswordManagerController;
import Model.PasswordManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainView {
    private PasswordManagerModel model;
    private PasswordManagerController controller;

    private JFrame frame;
    private JTextArea passwordListTextArea;
    private JTextField newPasswordTextField;
    private  JTextField userTextField;
    private JLabel userLabel;
    private JLabel passLabel;

    public MainView(PasswordManagerModel model) {
        this.model = model;
        initializeUI();
    }
    public MainView(PasswordManagerModel model, PasswordManagerController controller) {
        this.model = model;
        this.controller = controller;
        this.controller.setMainView(this);
        initializeUI();
    }

    public void setController(PasswordManagerController controller) {

        this.controller = controller;
    }

    private void initializeUI() {
        frame = new JFrame("Password Manager");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        passwordListTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(passwordListTextArea);
        newPasswordTextField = new JTextField();
        userTextField = new JTextField();
        JButton addButton = new JButton("Add Password");
        JButton removeButton = new JButton("Remove Selected");
        userLabel = new JLabel("enter the user to add: ");
        passLabel = new JLabel("enter the password to add: ");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    String username = userTextField.getText();
                    String newPassword = newPasswordTextField.getText();
                    controller.addPassword(username, newPassword);
                    newPasswordTextField.setText("");
                    userTextField.setText("");
                    updatePasswordList();
                } else {
                    System.out.println("Controller is null.");
                }
            }
        });

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


        scrollPane.setBounds(50, 50, 300, 200);
        userLabel.setBounds(50,275,200,25);
        userTextField.setBounds(50, 300, 200, 25);
        passLabel.setBounds(50,325,200,25);
        newPasswordTextField.setBounds(50, 350, 200, 25);
        addButton.setBounds(50, 400, 200, 25);
        removeButton.setBounds(50, 450, 200, 25);

        frame.setLayout(null);
        frame.add(scrollPane);
        frame.add(newPasswordTextField);
        frame.add(userTextField);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(userLabel);
        frame.add(passLabel);



        updatePasswordList();

        frame.setVisible(true);
    }

    public void updatePasswordList() {
        Map<String, String> passwords = model.getPasswordMap();
        StringBuilder passwordList = new StringBuilder();
        for (Map.Entry<String, String> entry : passwords.entrySet()) {
            passwordList.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        passwordListTextArea.setText(passwordList.toString());
    }
}
