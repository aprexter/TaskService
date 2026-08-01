package com.aprexter.taskservice.tasks.dtos;

import lombok.Data;

import java.util.Date;
@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Date dueDate;
}
