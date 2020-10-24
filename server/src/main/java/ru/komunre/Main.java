package ru.komunre;

import java.io.IOException;
import java.time.chrono.IsoEra;

public class Main {
    public static void main(String[] args){
        Server server;
        try {
            server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        catch (IOException e){
            e.printStackTrace();
            return;
        }
        while (true){
            server.accept();
            server.reSend();
        }
    }
}
