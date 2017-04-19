package com.dev.minhmin.gymmanager.model;

/**
 * Created by Minh min on 4/19/2017.
 */

public class Blog {
    private String title;
    private String content;
    private String intro;
    private String imageUrl;
    private int thumbUrl;

    public Blog(String title, String content, String intro, String imageUrl, int thumbUrl) {
        this.title = title;
        this.content = content;
        this.intro = intro;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
    }

    public Blog(String title, String content, String intro, String imageUrl) {
        this.title = title;
        this.content = content;
        this.intro = intro;
        this.imageUrl = imageUrl;
    }

    public Blog() {
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
}
