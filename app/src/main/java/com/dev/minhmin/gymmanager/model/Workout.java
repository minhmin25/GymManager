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
    private int thumbUrl;
    private ArrayList<WorkoutExercise> listWorkoutExercise;

    public Workout() {
    }

    public Workout(String id, String title, int time, String imageUrl, int thumbUrl, ArrayList<WorkoutExercise> listWorkoutExercise) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
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

    public int getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(int thumbUrl) {
        this.thumbUrl = thumbUrl;
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
        value.put("thumbUrl", thumbUrl);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (WorkoutExercise i : listWorkoutExercise) {
            list.add(i.toMap());
        }
        value.put("listWorkoutExercise", list);
        return value;
    }
}
