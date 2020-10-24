package ru.komunre;

import javax.swing.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Client client = new Client();
        try {
            client.connect(args[0], Integer.parseInt(args[1]));
            System.out.println("Connected");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        Frame frame = new Frame();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                MessageContainer messageContainer = new MessageContainer(args[2]);
                frame.createGUI(700, 400);
                messageContainer.addToFrame(frame.frame);
                messageContainer.insertMessage("Welcome to he server!", true);
                Thread loop = new LoopThread(frame.frame, messageContainer, client);
                loop.start();
            }
        } );
    }
}
