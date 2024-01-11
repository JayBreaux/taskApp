package com.jaybreauxdev.taskApi.taskApi.model;

public class TaskPostRequest {
    private String title;
    private int priority;
    private String createdBy;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskPostRequest{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", createdBy='" + createdBy + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
