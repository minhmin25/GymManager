package com.dev.minhmin.gymmanager.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 5/6/2017.
 */

public class LineItem implements Serializable {
    private Food food;
    private int number;

    public LineItem(Food food, int number) {
        this.food = food;
        this.number = number;
    }

    public LineItem() {
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getTotalCalo() {
        float t = (float) number * food.getCalo();
        return t;
    }

    public float getTotalProtetin() {
        float t = (float) number * food.getProtein();
        return t;
    }

    public float getTotalFat() {
        float t = (float) number * food.getFat();
        return t;
    }

    public float getTotalCab() {
        float t = (float) number * food.getCarb();
        return t;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();
        value.put("id", food.getId());
        value.put("number", number);
        value.put("food", food.toMap());
        return value;
    }

}
