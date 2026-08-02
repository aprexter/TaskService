package com.aprexter.taskservice.notes;

import com.aprexter.taskservice.common.BaseModel;
import com.aprexter.taskservice.tasks.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Notes extends BaseModel {
    private String noteTitle;
    private String noteContent;
    @ManyToOne
    private Task task;
}
