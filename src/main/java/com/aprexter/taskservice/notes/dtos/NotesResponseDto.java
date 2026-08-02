package com.aprexter.taskservice.notes.dtos;

import lombok.Data;

@Data
public class NotesResponseDto {
    private Long id;
    private String notesTitle;
    private String notesContent;
}
