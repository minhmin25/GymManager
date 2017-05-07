package com.dev.minhmin.gymmanager.utils;

import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.model.Meal;

/**
 * Created by Administrator on 5/7/2017.
 */

public class DataCenter {
    private Meal meal;

    public Meal getMeal(String date, String loai) {
        Meal meal = new Meal();
        return meal;
    }

    public Food getFood(String id) {
        Food f = new Food();
        return f;
    }
}
