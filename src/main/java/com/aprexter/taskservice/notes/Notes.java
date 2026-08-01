package com.aprexter.taskservice.notes;

import com.aprexter.taskservice.BaseModel;
import com.aprexter.taskservice.tasks.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Notes extends BaseModel {
    private String noteTitle;
    private String noteDescription;
    @ManyToOne
    private Task task;
}
