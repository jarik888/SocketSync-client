package model;

import java.util.HashMap;

public class Collection {

    private String collection;
    private long id;
    private HashMap<String,String> change;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashMap<String,String> getChange() {
        return change;
    }

    public void setChange(HashMap<String,String> change) { this.change = change; }

}
