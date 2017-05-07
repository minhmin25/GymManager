package com.dev.minhmin.gymmanager.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 5/6/2017.
 */

public class Meal implements Serializable {
    private String id;
    private String type;
    private ArrayList<LineItem> items;
    private String date;

    public Meal() {
        items = new ArrayList<LineItem>();
    }

    public Meal(String id, String type, ArrayList<LineItem> items, String date) {
        this.id = id;
        this.type = type;
        this.items = items;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        int code = Integer.parseInt(item.getFood().getId());
        int number = item.getNumber();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (Integer.parseInt(lineItem.getFood().getId()) == code) {
                lineItem.setNumber(number);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(LineItem item) {
        int code = Integer.parseInt(item.getFood().getId());
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (Integer.parseInt(lineItem.getFood().getId()) == code) {
                items.remove(i);

                return;
            }

        }
    }

    public float getTotalCalo() {
        float t = 0;
        for (int i = 0; i < getCount(); i++) {
            t = t + getItems().get(i).getTotalCalo();

        }
        return t;
    }

    public float getTotalFat() {
        float t = 0;
        for (int i = 0; i < getCount(); i++) {
            t = t + getItems().get(i).getTotalFat();

        }
        return t;
    }

    public float getTotalPro() {
        float t = 0;
        for (int i = 0; i < getCount(); i++) {
            t = t + getItems().get(i).getTotalProtetin();

        }
        return t;
    }

    public float getTotalCarb() {
        float t = 0;
        for (int i = 0; i < getCount(); i++) {
            t = t + getItems().get(i).getTotalCab();

        }
        return t;
    }

}
