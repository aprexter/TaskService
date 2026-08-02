package com.aprexter.taskservice.tasks;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositry extends JpaRepository<Task, Long> {
    @Override
    List<Task> findAll();

    void deleteByIdAndId(Long id, Long id1);
}
