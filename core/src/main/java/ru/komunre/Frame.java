package ru.komunre;

import javax.swing.*;
import java.awt.*;

public class Frame {
    JFrame frame;
    MessageContainer messageContainer;
    public void createGUI(int width, int height){
        frame = new JFrame("kmessanger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(width, height));

        frame.pack();
        frame.setVisible(true);
    }
}
