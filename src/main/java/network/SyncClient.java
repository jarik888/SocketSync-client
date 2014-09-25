package network;

import model.LocalCache;
import util.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SyncClient extends Thread {

    private static final String AUTH_KEY = "jart";

    private Connector conn;

    private BufferedReader in;
    private PrintWriter out;

    private JsonParser jsonParser = new JsonParser();

    private LocalCache localCache = new LocalCache();

    public SyncClient(Connector c) {
        conn = c;
        in = c.getInputStream();
        out = c.getOutputStream();
    }

    public void shutdown() {
        this.interrupt();
        conn.disconnect();
    }

    public void run() {

        System.setProperty("line.separator","\\r\\n");

        try {

            if (isAuth(AUTH_KEY) && loadCache("pull")) {
                String line;
                do {
                    line = in.readLine();

                    System.out.println(line); //input data from server

                    if (line.equals("pull")) { // send local cache dump
                        out.print("S: " + jsonParser.cacheContentToJson(localCache.pullLocalCache()));
                        out.flush();
                    } else { // receive a changes and save them in to local cache
                        line = line.substring(3); // cut 3 chars before JSON data
                        localCache.makeChangesInLocalCache(jsonParser.parseCollection(line));
                    }
                    // to see changes in cache
                    System.out.println("Local cache pull: " + "S: " + jsonParser.cacheContentToJson(localCache.pullLocalCache()));

                } while (line != null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    private boolean isAuth(String key) throws IOException {

        out.print(key);
        out.flush();

        String answer = in.readLine();

        if (!answer.equals("E: No such user.")) {
            System.out.println(answer);
            return true;
        } else {
            System.err.println("Wrong API key");
            return false;
        }
    }

    private boolean loadCache(String command) throws IOException {

        out.print(command);
        out.flush();

        String data = in.readLine();

        if (data != null) {
            System.out.println(data);
            data = data.substring(3); // cut 3 chars before JSON data
            localCache.createLocalCache(jsonParser.jsonToCache(data));

            return true;
        }

        return false;
    }
}
