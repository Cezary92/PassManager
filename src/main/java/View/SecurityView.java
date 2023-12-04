package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
/**
 * First desktop frame for a secured application.
 */
public class SecurityView implements ActionListener {

    private JFrame frame;
    private JButton myButton;
    private JButton resetButton;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JLabel userLabel;
    private JLabel userPasswordLabel;
    private JLabel messageLabel;
    HashMap<String, String> firstPass;
    private String userId, password;

    public SecurityView(){

        initializeUI();
        createAndAddComponents();  // Dodanie komponentów do interfejsu
        addEventListeners();
        setupDefaultCredentials();
    }
    /**
     * Initializes the user interface.
     */
    private void initializeUI(){
        frame = new JFrame();
        frame.setSize(420, 420);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Password Manager");
        ImageIcon image = new ImageIcon("KEY.png");
        frame.setIconImage(image.getImage());
        frame.setLayout(null);
    }
    /**
     * Creates and adds components to the user interface.
     */
    private void createAndAddComponents(){
        myButton = new JButton("Sign in");
        resetButton = new JButton("Reset");
        userTextField = new JTextField();
        passwordField = new JPasswordField();
        userLabel = new JLabel("Your ID: ");
        userPasswordLabel = new JLabel("Password: ");
        messageLabel = new JLabel();


        userLabel.setBounds(50, 100, 200,25);
        userPasswordLabel.setBounds(50, 150, 200,25);
        myButton.setBounds(125, 200, 100,25);
        resetButton.setBounds(225, 200, 100,25);
        userTextField.setBounds(125, 100, 200,25);
        passwordField.setBounds(125, 150, 200,25);
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25 ));

        frame.add(userLabel);
        frame.add(userPasswordLabel);
        frame.add(myButton);
        frame.add(resetButton);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(messageLabel);
    }
    /**
     * Adds event listeners to buttons.
     */
    private void addEventListeners() {
        resetButton.addActionListener(this);
        myButton.addActionListener(this);
    }
    /**
     * Sets up default access credentials.
     */
    private void setupDefaultCredentials(){
        firstPass = new HashMap<String, String>();
        firstPass.put("admin", "admin");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        userId = userTextField.getText();
        password = String.valueOf(passwordField.getPassword());

        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == myButton) {
            if (firstPass.containsKey(userId)) {
                if (firstPass.get(userId).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("You logged in");
                    frame.dispose();
                    MainView mainView = new MainView();
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password!");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong name!");
            }
        }
    }
}
