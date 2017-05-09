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

    public Exercise() {
    }

    public Exercise(String name, ArrayList<String> imageUrl, String videoUrl, String content) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.content = content;
    }


}
