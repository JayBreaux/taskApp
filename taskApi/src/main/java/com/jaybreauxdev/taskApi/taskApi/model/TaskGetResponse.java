package com.jaybreauxdev.taskApi.taskApi.model;

import java.util.List;

public class TaskGetResponse {

    private int recordCount;
    private List<TaskDetail> records;

    public TaskGetResponse() {
    }

    public TaskGetResponse(int recordCount, List<TaskDetail> records) {
        this.recordCount = recordCount;
        this.records = records;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<TaskDetail> getRecords() {
        return records;
    }

    public void setRecords(List<TaskDetail> records) {
        this.records = records;
    }
}
