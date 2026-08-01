package com.aprexter.taskservice.tasks.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
public class CreateTaskDto {
    @NotBlank(message = "Title is required with range in between 1 to 50 character")
    @Size(min = 1, max = 50)
    private String title;
    @NotBlank(message = "Description is required with rnage in between 1 to 200")
    @Size(min = 1, max = 200)
    private String description;
    private Boolean completed;
    @NotNull(message = "Date can't be null")
    @FutureOrPresent(message = "Date must be in future or Present")
    private Date dueDate;
}
