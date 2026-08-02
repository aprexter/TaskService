package com.aprexter.taskservice.notes;

import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepositry extends JpaRepository<Notes, Long> {

    void deleteNotesBy(Long id);
}
