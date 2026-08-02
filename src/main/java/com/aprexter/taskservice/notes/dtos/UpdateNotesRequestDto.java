package com.aprexter.taskservice.notes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNotesRequestDto {
    @Size(min = 3,max = 50)
    private String noteTitle;
    @Size(min = 3,max = 5000)
    private String noteContent;
}
