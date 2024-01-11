package com.jaybreauxdev.taskApi.taskApi.model;

public class TaskPostResponse {

    private int modifiedRecords;
    private String message;

    public TaskPostResponse() {
    }

    public TaskPostResponse(int modifiedRecords, String message) {
        this.modifiedRecords = modifiedRecords;
        this.message = message;
    }

    public int getModifiedRecords() {
        return modifiedRecords;
    }

    public void setModifiedRecords(int modifiedRecords) {
        this.modifiedRecords = modifiedRecords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
