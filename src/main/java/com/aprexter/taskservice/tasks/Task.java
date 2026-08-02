package com.aprexter.taskservice.tasks;

import com.aprexter.taskservice.common.BaseModel;
import com.aprexter.taskservice.notes.Notes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
public class Task extends BaseModel {
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 150)
    private String description;
    private Boolean completed;
    @Column(nullable = false)
    private Date dueDate;
    @OneToMany(mappedBy = "task")
    private List<Notes> notes;
}
