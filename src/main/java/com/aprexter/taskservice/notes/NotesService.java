package com.aprexter.taskservice.notes;

import com.aprexter.taskservice.notes.dtos.NotesRequestDto;
import com.aprexter.taskservice.notes.dtos.NotesResponseDto;
import com.aprexter.taskservice.notes.dtos.UpdateNotesRequestDto;
import com.aprexter.taskservice.tasks.Task;
import com.aprexter.taskservice.tasks.TaskRepositry;
import com.aprexter.taskservice.tasks.dtos.TaskResponseDto;
import com.aprexter.taskservice.tasks.exceptions.TaskNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {
    private final NotesRepositry notesRepository;
    private final TaskRepositry taskRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public NotesService(NotesRepositry notesRepository,
                        TaskRepositry taskRepository,
                        ModelMapper modelMapper) {
        this.notesRepository = notesRepository;
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
    }
    //Create notes
    public NotesResponseDto createNotes(Long taskId, NotesRequestDto notesRequestDto) {
        Task task=checkTaskById(taskId);
        Notes notes = modelMapper.map(notesRequestDto, Notes.class);
        task.getNotes().add(notes);
        taskRepository.save(task);
        return modelMapper.map(notesRepository.save(notes),NotesResponseDto.class);
    }
    //get all notes for a particular id
    public List<NotesResponseDto> getAllNotes(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        List<NotesResponseDto> notesResponseDtoList = task.getNotes().stream()
                .map(note -> modelMapper.map(task, NotesResponseDto.class))
                .toList();
        return notesResponseDtoList;
    }

    public NotesResponseDto getNotes(Long taskId, Long noteId) {
        Task task=checkTaskById(taskId);
        Notes note =notesRepository.findById(noteId).orElseThrow(() -> new NotesNotFoundException(noteId));
        return  modelMapper.map(note, NotesResponseDto.class);
    }

    //delete note from a particular task id
    public void deleteNotes(Long taskId, Long noteId) {
        Task task = checkTaskById(taskId);
        task.getNotes().removeIf(note -> note.getId().equals(noteId));
        taskRepository.save(task);
        notesRepository.deleteById(noteId);
    }

    //update the notes
    public NotesResponseDto updateNotes(Long taskId, Long notesId, UpdateNotesRequestDto updateNotes) {
        Task task = checkTaskById(taskId);
        Notes notes=notesRepository.findById(notesId).orElseThrow(() -> new NotesNotFoundException(notesId));
        if(updateNotes.getNotesContent()!=null){
            notes.setNoteContent(updateNotes.getNotesContent());
        }
        if(updateNotes.getNotesTitle()!=null){
            notes.setNoteTitle(updateNotes.getNotesTitle());
        }

        return modelMapper.map(notesRepository.save(notes), NotesResponseDto.class);
    }


    private Task checkTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException(taskId));
        return task;
    }

    static class NotesNotFoundException extends IllegalArgumentException {
        public NotesNotFoundException(Long taskId) {
            super("Notes with id " + taskId + " not found");
        }
    }
}
