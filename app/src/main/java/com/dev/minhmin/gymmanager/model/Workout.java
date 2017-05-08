package com.dev.minhmin.gymmanager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minh min on 4/19/2017.
 */

public class Workout {
    private String title;
    private int time;
    private String imageUrl;
    private int thumbUrl;

    public Workout() {
    }

    public Workout(String title, int time, String imageUrl, int thumbUrl) {
        this.title = title;
        this.time = time;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
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

    public int getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(int thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> value = new HashMap<>();
        value.put("title", title);
        value.put("time", time);
        value.put("imageUrl", imageUrl);
        value.put("thumbUrl", thumbUrl);
        return value;
    }
}
