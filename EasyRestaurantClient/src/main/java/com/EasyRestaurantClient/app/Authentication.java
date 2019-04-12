package com.EasyRestaurantClient.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authentication {
    private JTabbedPane tabbedPane1;
    private JTextField login_username;
    private JPasswordField login_pass;
    private JButton loginButton;
    private JTextField register_username;
    private JTextField name;
    private JTextField email;
    private JButton registerButton;
    private JPasswordField register_pass;
    private JPanel panel2;
    private JLabel response;
    private JLabel reg_response;

    public Authentication() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAuthentication userAuthentication = new UserAuthentication();
                String result = userAuthentication.register(register_username.getText(), register_pass.getText(), name.getText(), email.getText());
                reg_response.setText(result);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAuthentication userAuthentication = new UserAuthentication();
                String result = userAuthentication.login(login_username.getText(), login_pass.getText());
                response.setText(result);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Authentication");
        frame.setContentPane(new Authentication().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
