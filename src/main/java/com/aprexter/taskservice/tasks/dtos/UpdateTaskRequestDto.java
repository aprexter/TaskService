package com.aprexter.taskservice.tasks.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class UpdateTaskRequestDto {
    @Size(min = 1, max = 50)
    private String title;
    @Size(min = 1, max = 200)
    private String description;
    @FutureOrPresent(message = "Date must be in future or Present")
    private Date dueDate;
}
