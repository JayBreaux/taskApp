package com.jaybreauxdev.taskApi.taskApi.service;

import com.jaybreauxdev.taskApi.taskApi.entity.TaskEntity;
import com.jaybreauxdev.taskApi.taskApi.model.TaskDetail;
import com.jaybreauxdev.taskApi.taskApi.model.TaskGetResponse;
import com.jaybreauxdev.taskApi.taskApi.model.TaskPostRequest;
import com.jaybreauxdev.taskApi.taskApi.model.TaskPostResponse;
import com.jaybreauxdev.taskApi.taskApi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Takes ina list of tasks and adds them into the database
     */
    public TaskPostResponse addAll(List<TaskPostRequest> taskPostRequest) {
        List<TaskEntity> tasks = taskPostRequest.stream().map(taskRequest -> {
            if (verifyTaskData(taskRequest)) {
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setCreatedBy(taskRequest.getCreatedBy());
                taskEntity.setTitle(taskRequest.getTitle());
                taskEntity.setPriority(taskRequest.getPriority());
                taskEntity.setDescription(taskRequest.getDescription());
                return taskEntity;
            } else return null;
        }).toList();

        int recordCount = taskRepository.saveAll(tasks).size();

        return createTaskPostResponse(recordCount, recordCount + " new records added");
    }

    /**
     * Finds all tasks and returns back a get response
     */
    public TaskGetResponse fetchAll() {
        return createTaskGetResponse(taskRepository.findAll());
    }

    /**
     * Finds all tasks created by a given user and returns back a get response
     */
    public TaskGetResponse fetchByCreatedBy(String user) {
        return createTaskGetResponse(taskRepository.findAllByCreatedBy(user));
    }

    /**
     *
     */
    public TaskGetResponse fetchSubset(String orderBy, String direction, int itemsPerPage, int page) {

        Pageable pageable = PageRequest.of(page, itemsPerPage,
                Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), orderBy));

        return createTaskGetResponse(taskRepository.findAll(pageable).stream().toList());
    }

    /**
     * Helper method used to returns back a formatted get response
     */
    private TaskGetResponse createTaskGetResponse(List<TaskEntity> entities){
        List<TaskDetail> tasks = convertTaskEntityToDetail(entities);
        return new TaskGetResponse(tasks.size(), tasks);
    }

    /**
     * Helper method used to returns back a formatted post response
     */
    private TaskPostResponse createTaskPostResponse(int modifiedRecords, String message){
        return new TaskPostResponse(modifiedRecords, message);
    }

    /**
     * Helper method used to transform a list of taskEntities and return a list of taskDetails
     */
    private List<TaskDetail> convertTaskEntityToDetail(List<TaskEntity> entities) {
        return entities.stream()
                .map(entity -> new TaskDetail(entity.getTitle(), entity.getPriority(), entity.getCreatedBy(),
                        entity.getDescription()))
                .collect(Collectors.toList());
    }

    /**
     * Updates a task from the database given its id and the updated details
     */
    public TaskPostResponse updateTask(long id, TaskPostRequest taskRequest) {
        int entitiesModified = 0;
        String message = "";

        if (taskExists(id) && verifyTaskData(taskRequest)) {
            TaskEntity updatedTask = taskRepository.findById(id).get();
            updatedTask.setTitle(taskRequest.getTitle());
            updatedTask.setPriority(taskRequest.getPriority());
            updatedTask.setCreatedBy(taskRequest.getCreatedBy());
            updatedTask.setDescription(taskRequest.getDescription());

            entitiesModified = 1;
            message = "Updated Task with id: " + id;
        }

        return createTaskPostResponse(entitiesModified, message);
    }

    /**
     * Deletes a task from the database given its id
     */
    public TaskPostResponse deleteTask(long id) {
        int entitiesModified = 0;
        String message = "";

        if (taskExists(id)) {
            taskRepository.delete(taskRepository.findById(id).get());
            entitiesModified = 1;
            message = "Deleted task with id: " + id;
        }

        return createTaskPostResponse(entitiesModified, message);
    }

    /**
     * Helper method used to verify if a task exsists
     */
    private boolean taskExists(long id) {
        return taskRepository.findById(id).isPresent();
    }

    /**
     * TODO: Need this to actually verify the data
     */
    private boolean verifyTaskData(TaskPostRequest taskPostRequest) {
        return true;
    }
}
