package com.jaybreauxdev.taskApi.taskApi.repository;

import com.jaybreauxdev.taskApi.taskApi.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByCreatedBy(String createdBy);
}
