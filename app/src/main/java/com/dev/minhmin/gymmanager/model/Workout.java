package com.dev.minhmin.gymmanager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minh min on 4/19/2017.
 */

public class Workout {
    private String id;
    private String title;
    private int time;
    private String imageUrl;
    private ArrayList<WorkoutExercise> listWorkoutExercise;

    public Workout() {
    }

    public Workout(String id, String title, int time, String imageUrl, ArrayList<WorkoutExercise> listWorkoutExercise) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.imageUrl = imageUrl;
        this.listWorkoutExercise = listWorkoutExercise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<WorkoutExercise> getListWorkoutExercise() {
        return listWorkoutExercise;
    }

    public void setListWorkoutExercise(ArrayList<WorkoutExercise> listWorkoutExercise) {
        this.listWorkoutExercise = listWorkoutExercise;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();
        value.put("id", id);
        value.put("title", title);
        value.put("time", time);
        value.put("imageUrl", imageUrl);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        value.put("listWorkoutExercise", listWorkoutExercise);
        return value;
    }
}
