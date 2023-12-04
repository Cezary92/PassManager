package View;

import javax.swing.*;

public class MainView {
    private JFrame frame;

    MainView(){
        frame = new JFrame();
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Password Manager");
        ImageIcon image = new ImageIcon("KEY.png");
        frame.setIconImage(image.getImage());
        frame.setLayout(null);
    }
}
