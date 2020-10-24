package ru.komunre;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessageSender {
    JPanel panel;
    private JTextField messageField;
    private JButton send;
    public MessageSender(){
        panel = new JPanel();
        messageField = new JTextField(40);
        send = new JButton("Send");
        messageField.setPreferredSize(new Dimension(350, 50));
        messageField.setMargin(new Insets(350, 0, 0, 0));
        messageField.setFocusable(true);
        send.setMargin(new Insets(600, 350, 1, 1));
        send.setPreferredSize(new Dimension(99, 49));
        messageField.setEditable(true);
        panel.add(messageField);
        panel.add(send);
        panel.setFocusable(true);
    }
    public void addToFrame(JFrame frame) {
        frame.getContentPane().add(panel);
        getFocus();
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                getFocus();
            }

            @Override
            public void windowOpened(WindowEvent e) {
                getFocus();
            }
        });
    }
    public void getFocus(){
        System.out.println("Getting focus after events...");
        panel.grabFocus();
        panel.requestFocusInWindow();
        messageField.grabFocus();
        messageField.requestFocusInWindow();
        messageField.transferFocus();
    }
}
