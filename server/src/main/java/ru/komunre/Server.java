package ru.komunre;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;
    private Socket[] clients;

    public Server(int port, int usersCount) throws IOException {
        socket = new ServerSocket(port);
        socket.setSoTimeout(10);
        clients = new Socket[usersCount];
        System.out.println("Server opened");
    }

    public void accept() {
        try {
            for (int x = 0; x != clients.length; x++) {
                if (clients[x] == null) {
                    clients[x] = socket.accept();
                    System.out.println("User accepted");
                    return;
                }
            }
        }
        catch (IOException e){
            return;
        }
    }
    public void sendMessage(String message, int socketNum){
        try{
            DataOutputStream out = new DataOutputStream(clients[socketNum].getOutputStream());
            out.writeUTF(message);
        }
        catch (IOException e){
            System.out.println("Error of sending message");
            e.printStackTrace();
            clients[socketNum] = null;
        }
    }
    public String receiveMessage(int socketNum){
        try{
            DataInputStream in = new DataInputStream(clients[socketNum].getInputStream());
            if (in.available() == 0){
                return null;
            }
            return in.readUTF();
        }
        catch (IOException e){
            System.out.println("Error in reading message");
            e.printStackTrace();
            clients[socketNum] = null;
        }
        return null;
    }
    public void reSend(){
        for (int x = 0; x != clients.length; x++) {
            if (clients[x] == null){
                continue;
            }
            String message = receiveMessage(x);
            if (message == null){
                continue;
            }
            System.out.println("Messge received: \"" + message + "\"");
            for (int y = 0; y != clients.length; y++){
                if (clients[y] == null){
                    continue;
                }
                sendMessage(message, y);
            }
        }
    }
}
