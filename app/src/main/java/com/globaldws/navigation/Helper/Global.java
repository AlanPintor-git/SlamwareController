package com.globaldws.navigation.Helper;

import com.globaldws.navigation.Activity.MainActivity;
import com.globaldws.navigation.Classes.DataPreference;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Global {

    public static DataPreference dataPreference ;
    public static MainActivity mainActivity;
    
    public static Socket socket;
    public static final int SERVERPORT = 6000; //###8 original port
    public static int success = 1;
    public static int fail = 0;
    public static String delimiter = ">";

    public static void sendData(Socket socket, String data) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(data);
            System.out.println("Send To Server: " + data);
        } catch (IOException e) {
            System.err.println("Some Problem In Sending message!!!");
        }
    }
}
