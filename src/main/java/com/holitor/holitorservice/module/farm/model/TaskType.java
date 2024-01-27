package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`task_type`")
public class TaskType {

  @GeneratedValue(generator = "task-type-generator")
  @GenericGenerator(name = "task-type-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`name`")
  private String name;
  
}