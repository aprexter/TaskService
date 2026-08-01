package com.aprexter.taskservice.notes;

import com.aprexter.taskservice.notes.dtos.NotesResponseDto;
import com.aprexter.taskservice.tasks.dtos.TaskResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private final NotesService notesService;
    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesResponseDto>> getNotes(@PathVariable("taskId") Long taskId) {
        List<NotesResponseDto> responseDtoList= notesService.getAllNotes(taskId);
        return ResponseEntity.created(URI.create("/notes"+ taskId)).body(responseDtoList);

    }
    @PostMapping("")
    public ResponseEntity<TaskResponseDto> addNotes(@PathVariable("taskId") Long taskId, @RequestBody Notes notes) {
        return null;
    }


}
