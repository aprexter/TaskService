package com.aprexter.taskservice.notes;

import com.aprexter.taskservice.notes.dtos.NotesResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private final NotesRepositry notesRepository;
    @Autowired
    public NotesService(NotesRepositry notesRepository) {
        this.notesRepository = notesRepository;
    }
    public Notes createNotes(Notes notes) {
        return notesRepository.save(notes);
    }
    public List<NotesResponseDto> getAllNotes(Long taskId){

        return null;
    }
}
