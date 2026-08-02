package com.aprexter.taskservice.tasks;

import com.aprexter.taskservice.notes.Notes;
import com.aprexter.taskservice.tasks.dtos.CreateTaskDto;
import com.aprexter.taskservice.tasks.dtos.TaskResponseDto;
import com.aprexter.taskservice.tasks.dtos.UpdateTaskRequestDto;
import com.aprexter.taskservice.tasks.exceptions.TaskNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMapExtensionsKt;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepositry taskRepositry;
    private final ModelMapper modelMapper;
    public TaskService(TaskRepositry taskRepositry, ModelMapper modelMapper) {
        this.taskRepositry = taskRepositry;
        this.modelMapper = modelMapper;
    }

    public List<TaskResponseDto> getTasks(){
        List<Task> taskList=taskRepositry.findAll();
        List<TaskResponseDto> responseDtoList = taskList.stream()
                .map(task -> modelMapper.map(task, TaskResponseDto.class))
                .toList();
        return responseDtoList;
    }

    public TaskResponseDto createTask(CreateTaskDto createTaskDto) {
        Task task=modelMapper.map(createTaskDto, Task.class);
        task.setCompleted(true);
        return modelMapper.map(taskRepositry.save(task),TaskResponseDto.class);
    }
    public TaskResponseDto getTaskById(Long taskId) {
        Task task=checkTaskById(taskId);


        return  modelMapper.map(task,TaskResponseDto.class);
    }
    public TaskResponseDto updateTask(Long id, UpdateTaskRequestDto request) {

        Task task=checkTaskById(id);
        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if(request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }

        return modelMapper.map(taskRepositry.save(task), TaskResponseDto.class);
    }

    public void deleteTask(Long taskId) {
        taskRepositry.findById(taskId).orElseThrow(()->new TaskNotFoundException(taskId));
        taskRepositry.deleteById(taskId);
    }

    private Task checkTaskById(Long taskId) {
        Task task = taskRepositry.findById(taskId).orElseThrow(()->new TaskNotFoundException(taskId));
        return task;
    }
}
