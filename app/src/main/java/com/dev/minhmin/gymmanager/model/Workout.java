package com.dev.minhmin.gymmanager.model;

/**
 * Created by Minh min on 4/19/2017.
 */

public class Workout {
    private String title;
    private int time;
    private String imageUrl;

    public Workout() {
    }

    public Workout(String title, int time, String imageUrl) {
        this.title = title;
        this.time = time;
        this.imageUrl = imageUrl;
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
}
