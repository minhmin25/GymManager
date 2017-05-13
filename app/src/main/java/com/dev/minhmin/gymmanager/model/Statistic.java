package com.dev.minhmin.gymmanager.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/13/2017.
 */

public class Statistic {
    private String date;
    private float totalFood, totalExercise, totalGoal, totalRemain;
    private ArrayList<Practice> listEx = new ArrayList<>();

    public Statistic(String date, float totalFood, float totalExercies, float totalGoal, float totalRemain, ArrayList<Practice> listEx) {
        this.date = date;
        this.totalFood = totalFood;
        this.totalExercise = totalExercies;
        this.totalGoal = totalGoal;
        this.totalRemain = totalRemain;
        this.listEx = listEx;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(float totalFood) {
        this.totalFood = totalFood;
    }

    public float getTotalExc() {
        return totalExercise;
    }

    public void setTotalExc(float totalExc) {
        this.totalExercise = totalExc;
    }

    public float getTotalGoal() {
        return totalGoal;
    }

    public void setTotalGoal(float totalGoal) {
        this.totalGoal = totalGoal;
    }

    public float getTotalRemain() {
        return totalRemain;
    }

    public void setTotalRemain(float totalRemain) {
        this.totalRemain = totalRemain;
    }

    public ArrayList<Practice> getListEx() {
        return listEx;
    }

    public void setListEx(ArrayList<Practice> listEx) {
        this.listEx = listEx;
    }

    public Statistic() {
    }
}
