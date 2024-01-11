package com.jaybreauxdev.taskApi.taskApi.controllers;

import com.jaybreauxdev.taskApi.taskApi.model.TaskGetResponse;
import com.jaybreauxdev.taskApi.taskApi.model.TaskPostRequest;
import com.jaybreauxdev.taskApi.taskApi.model.TaskPostResponse;
import com.jaybreauxdev.taskApi.taskApi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/tasks/v1")
public class taskController {

    @Autowired
    private TaskService taskListService;

    /**
     * Allows adding a list of tasks at a time:
     * Create tasks from a list of TaskPostRequest JSON objects provided in the request body
     * Returns a TaskPostResponse object type
     */
    @PostMapping
    public TaskPostResponse createTasks(@RequestBody List<TaskPostRequest> taskPostRequestList) {
        return taskListService.addAll(taskPostRequestList);
    }

    /**
     * Allows adding a task one at a time
     * Convert a TaskPostRequest JSON object provided in the request to a list and then call createTasks
     * Returns a TaskPostResponse object type
     */

    @PostMapping("/task")
    public TaskPostResponse createTask(@RequestBody TaskPostRequest taskPostRequest) {
        ArrayList<TaskPostRequest> TaskPostRequestList = new ArrayList<>();
        TaskPostRequestList.add(taskPostRequest);
        return createTasks(TaskPostRequestList);
    }

    /**
     * Gets all tasks in the system
     * Returns the data in a TaskGetResponse object type
     */
    @GetMapping
    public TaskGetResponse getTasks() {
        return taskListService.fetchAll();
    }

    /**
     * Updates a task with the TaskPostRequest JSON object provided in the request body
     * Returns the data in a TaskPostResponse object type
     */
    @PutMapping(value = "/{id}")
    public TaskPostResponse updateTask(@PathVariable("id") long id, TaskPostRequest taskPostRequest) {
        return taskListService.updateTask(id, taskPostRequest);
    }

    /**
     * Deletes a task of the given id
     * Returns the data in a TaskPostResponse object type
     */
    @DeleteMapping(value = "/{id}")
    public TaskPostResponse deleteTask(@PathVariable("id") long id) {
        return taskListService.deleteTask(id);
    }

    /**
     * Gets all tasks in the system for a specific user only
     * Returns the data in a TaskGetResponse object type
     */
    @GetMapping("/{user}")
    public TaskGetResponse getTasksByUser(@PathVariable String user) {
       return taskListService.fetchByCreatedBy(user);
    }

    /**
     * Gets a range of tasks depending on paging
     * Parameters are optional and the default values are set
     * Returns the data in a TaskGetResponse object type
     */
    @GetMapping("/subset")
    public TaskGetResponse getSubsetOfTasks( @RequestParam(defaultValue = "title") String orderBy,
                                             @RequestParam(defaultValue = "asc") String direction,
                                             @RequestParam(defaultValue = "3") int itemsPerPage,
                                             @RequestParam(defaultValue = "0") int page) {
       return taskListService.fetchSubset(orderBy, direction, itemsPerPage, page);
    }


}
