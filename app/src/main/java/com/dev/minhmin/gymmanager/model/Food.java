package com.dev.minhmin.gymmanager.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 5/6/2017.
 */

public class Food implements Serializable {
    private String id;
    private String name;
    private String imageUrl;
    private String unit1;
    private int unit2;
    private float calo;
    private float protein;
    private float fat;
    private float carb;

    public Food(String id, String name, String imageUrl, String unit1, int unit2, float calo, float protein, float fat, float carb) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.calo = calo;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

    public Food() {

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public int getUnit2() {
        return unit2;
    }

    public void setUnit2(int unit2) {
        this.unit2 = unit2;
    }

    public float getCalo() {
        return calo;
    }

    public void setCalo(float calo) {
        this.calo = calo;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();

        return value;
    }
}
