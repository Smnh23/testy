package com.holitor.holitorservice.module.farm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`task`")
public class Task {
  
  @GeneratedValue(generator = "task-generator")
  @GenericGenerator(name = "task-generator", strategy = "increment")
  private @Id long id;

  @ManyToOne(targetEntity = TaskType.class)
  @JoinColumn(name = "`task_type_id`")
  private TaskType type;
  
  @Column(name = "`predicted_date`")
  private Date predictedDate;
  
  @Column(name = "`actual_date`")
  private Date actualDate;

  @Column(name = "`duration`")
  private long duration;

  @ManyToMany(targetEntity = Tool.class)
  @JoinTable(name = "`use_tool`", inverseJoinColumns = @JoinColumn(name = "`tool_id`"))
  private List<Tool> tools = new ArrayList<Tool>();
  
}