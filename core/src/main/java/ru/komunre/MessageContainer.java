package ru.komunre;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.*;

public class MessageContainer {
    JPanel panel;
    private JTextPane textPane = null;
    private JScrollPane scroll = null;
    Style messageStyle;
    private JTextPane messageField = null;
    private JButton send;
    String nickname;

    public MessageContainer(String nickname){
        this.nickname = nickname;
        panel = new JPanel();
        panel.setBounds(0, 10, 700, 390);
        textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(380, 280));
        scroll = new JScrollPane(textPane);
        messageStyle = textPane.addStyle("normal", null);
        StyleConstants.setFontSize(messageStyle, 16);
        messageField = new JTextPane();
        send = new JButton("Send");
        messageField.setPreferredSize(new Dimension(300, 40));
        messageField.setFocusable(true);
        send.setPreferredSize(new Dimension(99, 49));
        textPane.setEditable(false);
        textPane.setFocusable(true);
        messageField.setEditable(true);
        panel.add(textPane);
        panel.add(messageField);
        panel.add(send);
        panel.setFocusable(false);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                System.out.println("Giving focus...");
                messageField.requestFocusInWindow();
            }
        });
    }

    public void addToFrame(JFrame frame) {
        frame.getContentPane().add(panel);
        frame.setLayout(null);
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
        messageField.grabFocus();
        messageField.requestFocusInWindow();
    }

    public void insertMessage(String message, boolean atEnd) {
        String resultMessage = message + "\n";
        try {
            Document doc = textPane.getDocument();
            if (atEnd) {
                doc.insertString(doc.getLength(), resultMessage, messageStyle);
            } else {
                doc.insertString(0, resultMessage, messageStyle);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public JTextPane getTextPane(){
        return textPane;
    }

    public String getFieldText(){
        if (send.getModel().isPressed()){
            return nickname + ": " + messageField.getText();
        }
        return null;
    }
}
