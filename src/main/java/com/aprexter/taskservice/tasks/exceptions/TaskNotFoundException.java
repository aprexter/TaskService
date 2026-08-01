package com.aprexter.taskservice.tasks.exceptions;

public class TaskNotFoundException extends IllegalArgumentException{
    public TaskNotFoundException(Long taskId) {
        super("Task not found with id " + taskId);

    }
}
