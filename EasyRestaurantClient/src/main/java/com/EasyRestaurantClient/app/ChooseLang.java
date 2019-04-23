package com.EasyRestaurantClient.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChooseLang {
    private JPanel ChooseLang;
    private JFormattedTextField pleaseChooseTheLanguageFormattedTextField;
    private JRadioButton englishRadioButton;
    private JRadioButton greekRadioButton;
    private JButton ΟΚButton;
    public int lang=0;





    public boolean isDone() {
        return done;
    }

    private boolean done = false;


    public static void main(String[] args) {
        JFrame frame = new JFrame("ChooseLang");
        frame.setContentPane(new ChooseLang().ChooseLang);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public  ChooseLang() {

        ΟΚButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           // need to break the window somehow and return to the reservation main class
            Reservation res = new Reservation();
            res.setLanguage(lang);
            res.makeResInterface();

            }
        });
        greekRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 lang = 1;
            }
        });
        englishRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lang = 0;
            }
        });



    }

    public void SelectLang(){
        JFrame frame = new JFrame("ChooseLang");
        frame.setContentPane(new ChooseLang().ChooseLang);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        while(true){}

    }
}
