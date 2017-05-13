package com.dev.minhmin.gymmanager.model;

import java.util.ArrayList;

/**
 * Created by Minh min on 5/9/2017.
 */

public class Exercise {
    private String name;
    private ArrayList<String> imageUrl;
    private String videoUrl;
    private String content;
    private int calo;
    private String reference;

    public Exercise() {

    }

    public Exercise(String name, ArrayList<String> imageUrl, String videoUrl, String content, int calo, String reference) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.content = content;
        this.calo = calo;
        this.reference = reference;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
