package com.EasyRestaurantClient.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

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
    private JComboBox comboBox1;
    private JFrame frame;
    private int lang;



    public Authentication() {
        ComboItem item1 = new ComboItem("English", "Language1");
        ComboItem item2 = new ComboItem("Greek", "Language2");
        comboBox1.addItem(item1);
        comboBox1.addItem(item2);
        final Locale englishLocale = new Locale("en_US");
        final Locale greekLocale = new Locale("el_GR");
        Locale.setDefault(Locale.getDefault());
        if (Locale.getDefault().equals(greekLocale)) {
            comboBox1.setSelectedItem(item2);

        }
        else {
            comboBox1.setSelectedItem(item1);
        }
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAuthentication userAuthentication = new UserAuthentication();
                String result = userAuthentication.register(register_username.getText(), register_pass.getText(), name.getText(), email.getText());
                reg_response.setText(result);
                JOptionPane.showMessageDialog(tabbedPane1, result);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAuthentication userAuthentication = new UserAuthentication();
                String result = userAuthentication.login(login_username.getText(), login_pass.getText());
                response.setText(result);
                if (result.equals("Correct")){
                    frame.dispose();
                    Consult frame = new Consult(login_username.getText());
                    frame.setVisible(true);
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedItem().toString().equals("Greek")){
                    Locale.setDefault(greekLocale);

                } else {
                    Locale.setDefault(englishLocale);

                }
                frame.dispose();
                JFrame frame2 = new JFrame("Authentication");
                Authentication authentication = new Authentication();
                authentication.frame = frame2;
                frame2.setContentPane(authentication.panel2);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
                frame2.setSize(400,300);
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
        Authentication authentication = new Authentication();
        frame.setContentPane(authentication.panel2);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400,300);
        authentication.frame = frame;
    }
}
