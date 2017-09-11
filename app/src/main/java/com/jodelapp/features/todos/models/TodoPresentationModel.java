package com.jodelapp.features.todos.models;


public class TodoPresentationModel {

    private String id;
    private String title;
    private String status;

    public TodoPresentationModel(String id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
