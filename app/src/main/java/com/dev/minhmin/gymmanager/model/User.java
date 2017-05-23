package com.dev.minhmin.gymmanager.model;

/**
 * Created by Minh min on 5/15/2017.
 */

public class User {
    private String id, name, email, imageUrl, gender;
    private int age;
    private int weight, goal, height;

    public User() {
    }

    public User(String id, String name, int age, String email, String imageUrl, String gender, int height, int weight, int goal) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
