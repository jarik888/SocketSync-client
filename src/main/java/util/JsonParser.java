package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Collection;
import model.DatabaseContent;

public class JsonParser {

    public DatabaseContent jsonToCache(String json) {

        DatabaseContent db = null;

        try {
            Gson gson = new GsonBuilder().create();
            db = gson.fromJson(json, DatabaseContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return db;
    }

    public Collection parseCollection(String json) {

        Collection c = null;

        try {
            Gson gson = new GsonBuilder().create();
            c = gson.fromJson(json, Collection.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    public String cacheContentToJson(DatabaseContent db) {

        Gson gson = new Gson();
        return gson.toJson(db);

    }

}
