package controller;

import network.Connector;
import network.SyncClient;

public class Main {

    static SyncClient client = new SyncClient(new Connector());

    public static void main(String[] args) {

        //close established connection before exit this program
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                client.shutdown();
            }
        });

        client.start();

    }
}
