package model;

import java.util.List;

public class DatabaseContent{
    private List<Parcel> parcel;
    private List<Place> place;
    private List<Slot> slot;

    public List getParcel() {
        return parcel;
    }
    public void setParcel(List parcel) {
        this.parcel = parcel;
    }
    public List getPlace() {
        return place;
    }
    public void setPlace(List place) {
        this.place = place;
    }
    public List getSlot() {
        return slot;
    }
    public void setSlot(List slot) {
        this.slot = slot;
    }
}