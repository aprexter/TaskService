package com.aprexter.taskservice.tasks;

import com.aprexter.taskservice.common.ExceptionResponseDto;
import com.aprexter.taskservice.notes.Notes;
import com.aprexter.taskservice.tasks.dtos.CreateTaskDto;
import com.aprexter.taskservice.tasks.dtos.TaskResponseDto;
import com.aprexter.taskservice.tasks.dtos.UpdateTaskRequestDto;
import com.aprexter.taskservice.tasks.exceptions.TaskNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    // get all task
    @GetMapping("")
    public ResponseEntity<List<TaskResponseDto>> getTask() {
        List<TaskResponseDto> responseDtoList=taskService.getTasks();
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping("")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody CreateTaskDto createTaskDto) {
        TaskResponseDto responseDto=taskService.createTask(createTaskDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable("id") Long id) {
        TaskResponseDto responseDto=taskService.getTaskById(id);
        return ResponseEntity
                .created(URI.create("/tasks/"+responseDto.getId()))
                .body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable("id") Long id,@Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        TaskResponseDto responseDto=taskService.updateTask(id,updateTaskRequestDto);
        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDto> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleTaskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(ex.getMessage()));
    }




}
