package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LocalCache {

    private HashMap<Long, Parcel> parcelsLocalCache;
    private HashMap<Long, Place> placesLocalCache;
    private HashMap<Long, Slot> slotsLocalCache;

    public void createLocalCache(DatabaseContent db) {

        parcelsLocalCache = new HashMap<Long, Parcel>();
        for (Parcel p : (ArrayList<Parcel>)db.getParcel()) {
            parcelsLocalCache.put(p.getId(), p);
        }

        placesLocalCache = new HashMap<Long, Place>();
        for (Place p : (ArrayList<Place>)db.getPlace()) {
            placesLocalCache.put(p.getId(), p);
        }

        slotsLocalCache = new HashMap<Long, Slot>();
        for(Slot s : (ArrayList<Slot>)db.getSlot()) {
            slotsLocalCache.put(s.getId(), s);
        }
    }

    public synchronized void makeChangesInLocalCache(Collection c) {

        if (c.getCollection().equals("parcel")) {
            changeParcel(c);
        } else if (c.getCollection().equals("place")) {
            changePlace(c);
        } else if (c.getCollection().equals("slot")) {
            changeSlot(c);
        }
    }

    private void changeParcel(Collection c) {

        if (parcelsLocalCache.containsKey(c.getId())) {

            Parcel localP = parcelsLocalCache.get(c.getId());

            if (c.getChange().containsKey("destination")) {
                localP.setDestination(c.getChange().get("destination"));
            }
            if (c.getChange().containsKey("code")) {
                localP.setCode(c.getChange().get("code"));
            }
            if (c.getChange().containsKey("barcode")) {
                localP.setBarcode(c.getChange().get("barcode"));
            }
        }
    }

    private void changePlace(Collection c) {

        if (placesLocalCache.containsKey(c.getId())) {

            Place localP = placesLocalCache.get(c.getId());

            if (c.getChange().containsKey("name")) {
                localP.setName(c.getChange().get("name"));
            }
            if (c.getChange().containsKey("location")) {
                localP.setLocation(c.getChange().get("location"));
            }
            if (c.getChange().containsKey("hostname")) {
                localP.setHostname(c.getChange().get("hostname"));
            }
        }
    }

    private void changeSlot(Collection c) {

        if (slotsLocalCache.containsKey(c.getId())) {

            Slot localS = slotsLocalCache.get(c.getId());

            if (c.getChange().containsKey("ip")) {
                localS.setIp(c.getChange().get("ip"));
            }
            if (c.getChange().containsKey("ap")) {
                localS.setAp(c.getChange().get("ap"));
            }
            if (c.getChange().containsKey("place")) {
                localS.setPlace(c.getChange().get("place"));
            }
            if (c.getChange().containsKey("status")) {
                localS.setStatus(c.getChange().get("status"));
            }
        }
    }

    public synchronized DatabaseContent pullLocalCache() {

        DatabaseContent db = new DatabaseContent();

        Set<Long> parcelKeys = parcelsLocalCache.keySet();
        Set<Long> placeKeys = placesLocalCache.keySet();
        Set<Long> slotKeys = slotsLocalCache.keySet();

        ArrayList<Parcel> parcelsList = new ArrayList<Parcel>();
        ArrayList<Place> placesList = new ArrayList<Place>();
        ArrayList<Slot> slotsList = new ArrayList<Slot>();

        for (Long id : parcelKeys) {
            parcelsList.add(parcelsLocalCache.get(id));
        }
        for (Long id : placeKeys) {
            placesList.add(placesLocalCache.get(id));
        }
        for (Long id : slotKeys) {
            slotsList.add(slotsLocalCache.get(id));
        }

        db.setParcel(parcelsList);
        db.setPlace(placesList);
        db.setSlot(slotsList);

        return db;
    }
}
