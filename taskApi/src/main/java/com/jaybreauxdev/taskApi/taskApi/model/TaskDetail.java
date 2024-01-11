package com.jaybreauxdev.taskApi.taskApi.model;

public class TaskDetail {
    private String title;
    private int priority;
    private String createdBy;
    private String description;

    public TaskDetail() {
    }

    public TaskDetail(String title, int priority, String createdBy, String description) {
        this.title = title;
        this.priority = priority;
        this.createdBy = createdBy;
        this.description = description;
    }

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
        return "TaskDetail{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", createdBy='" + createdBy + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
