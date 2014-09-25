package network;

import java.io.*;
import java.net.Socket;

public class Connector {

    private static final String IP = "devdeb.legendaarne.eu"; //or domain
    private static final int port = 22800;

    private Socket s;

    private BufferedReader in;
    private PrintWriter out;

    public Connector() {
        try {
            s = new Socket(IP, port);

            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream())),true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        } catch (IOException e) {
            System.err.println("No connection to the server!");
//            e.printStackTrace();
        }
    }

    public void disconnect() {
        System.out.println("Close connection...");
        try {
//            out.close();
//            in.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getInputStream() {
        return in;
    }

    public PrintWriter getOutputStream() {
        return out;
    }


}
