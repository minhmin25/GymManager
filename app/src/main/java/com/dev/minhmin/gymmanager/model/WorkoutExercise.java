package com.dev.minhmin.gymmanager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minh min on 5/9/2017.
 */

public class WorkoutExercise {
    private String id;
    private String name;
    private int set;
    private String unit;
    private int quantity;
    private String content;

    public WorkoutExercise() {
    }

    public WorkoutExercise(String id, String name, int set, String unit, int quantity, String content) {
        this.id = id;
        this.name = name;
        this.set = set;
        this.unit = unit;
        this.quantity = quantity;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();
        value.put("id", id);
        value.put("name", name);
        value.put("set", set);
        value.put("quantity", quantity);
        value.put("unit", unit);
        return value;
    }

}
