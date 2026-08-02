package com.aprexter.taskservice.notes;

import com.aprexter.taskservice.common.ExceptionResponseDto;
import com.aprexter.taskservice.notes.dtos.NotesRequestDto;
import com.aprexter.taskservice.notes.dtos.NotesResponseDto;
import com.aprexter.taskservice.notes.dtos.UpdateNotesRequestDto;
import com.aprexter.taskservice.tasks.dtos.TaskResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{noteId}")
    public ResponseEntity<NotesResponseDto> getNote(@PathVariable("taskId") Long taskId, @PathVariable("noteId") Long noteId) {
        NotesResponseDto responseDto=notesService.getNotes(taskId, noteId);
        return ResponseEntity.created(URI.create("/tasks"+taskId+ "/notes"+ noteId)).body(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<NotesResponseDto> addNotes(@PathVariable("taskId") Long taskId,@Valid @RequestBody NotesRequestDto  notesRequestDto) {
        NotesResponseDto responseDto=notesService.createNotes(taskId, notesRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @PatchMapping("/{noteId}")
    public ResponseEntity<NotesResponseDto> updateNotes(@PathVariable("taskId") Long taskId,
                                                        @PathVariable("noteId") Long noteId,
                                                        @Valid @RequestBody UpdateNotesRequestDto updateNotesRequestDto) {
        NotesResponseDto updateresponseDto=notesService.updateNotes(taskId,noteId,updateNotesRequestDto);
        return ResponseEntity.ok(updateresponseDto);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<NotesResponseDto> deleteNotes(@PathVariable("taskId") Long taskId, @PathVariable Long noteId) {
        notesService.deleteNotes(taskId, noteId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotesService.NotesNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotesNotFoundException(NotesService.NotesNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(ex.getMessage()));
    }




}
