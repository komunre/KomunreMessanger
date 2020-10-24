package ru.komunre;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.io.IOException;

public class LoopThread extends Thread{
    JFrame frame;
    MessageContainer messageContainer;
    Client client;
    String prevMessage;

    public LoopThread(JFrame frame, MessageContainer container, Client client){
        this.frame = frame;
        messageContainer = container;
        this.client = client;
    }

    @Override
    public void run(){
        while (true){
            String message = messageContainer.getFieldText();
            if (message != null){
                if (!message.equals(prevMessage)) {
                    try {
                        prevMessage = message;
                        System.out.println("Message sended");
                        client.sendMessage(message);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Can't send message");
                    }
                }
            }
            try {
                String rMessage = client.receiveMessage();
                if (rMessage != null){
                    messageContainer.insertMessage(rMessage, false);
                    System.out.println("Message received");
                }
            }
            catch (IOException e){
                e.printStackTrace();
                System.out.println("Can't read message");
            }
        }
    }
}
